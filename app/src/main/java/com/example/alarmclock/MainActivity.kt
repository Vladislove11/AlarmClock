package com.example.alarmclock

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.TimeUnit
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.*
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var addAlarmTimeButtonBtN: Button
    private lateinit var exitBTN: Button
    private lateinit var listViewLV: ListView

    private var listAlarmAdapter: ListAlarmAdapter? = null
    private val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private var calendar: Calendar = Calendar.getInstance()
    lateinit var listViewModel: ListViewModel

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val alarmHelper = AlarmHelper(this)

        addAlarmTimeButtonBtN = findViewById(R.id.addAlarmTimeButtonBtN)
        exitBTN = findViewById(R.id.exitBTN)
        listViewLV = findViewById(R.id.listViewLV)

        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        listAlarmAdapter = ListAlarmAdapter(this@MainActivity, listViewModel.listAlarm, alarmHelper)
        listViewLV.adapter = listAlarmAdapter

        listViewModel.listAlarmData.observe(this,{ listAlarm ->
            listAlarmAdapter?.clear()
            listAlarmAdapter?.addAll(listAlarm)
        })

        addAlarmTimeButtonBtN.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время будильника")
                .build()


            materialTimePicker.addOnPositiveButtonClickListener {
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.MINUTE, materialTimePicker.minute)
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.hour)

                val alarmClock = AlarmClock(listViewModel.listAlarm.size, dateFormat.format(calendar.time),  calendar.timeInMillis,true)
                listViewModel.listAlarm.add(alarmClock)
                listAlarmAdapter?.notifyDataSetChanged()

                listViewModel.listAlarm.forEach{alarmHelper.setAlarm(it)}
            }

            materialTimePicker.show(supportFragmentManager, "tag_picker")
        }

        exitBTN.setOnClickListener{
            finishAffinity()
        }

    }

}

