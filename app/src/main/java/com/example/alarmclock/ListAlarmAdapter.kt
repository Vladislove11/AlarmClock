package com.example.alarmclock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ListAlarmAdapter(context: Context, alarmList: MutableList <AlarmClock>, private val alarmHelper: AlarmHelper): ArrayAdapter<AlarmClock>(
    context,
    R.layout.list_item,
    alarmList
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val alarmClock = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        //val alarmHelper = AlarmHelper(context)
        val switchCompatSC = view?.findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.switchCompatSC)

        switchCompatSC?.setOnCheckedChangeListener(null)
        switchCompatSC?.setOnCheckedChangeListener({_, isChecked ->
            if (alarmClock != null) {
                if (switchCompatSC.isChecked) {
                    alarmHelper.setAlarm(alarmClock)
                } else {
                    alarmHelper.cancelAlarm(alarmClock)
                }
            }
        })
        val nameAlarmLV = view?.findViewById<TextView>(R.id.nameAlarmTV)
        nameAlarmLV?.text = alarmClock?.timeFormat

        return  view!!
    }
}