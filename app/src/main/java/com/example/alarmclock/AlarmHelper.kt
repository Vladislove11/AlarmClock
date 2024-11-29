package com.example.alarmclock

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmHelper(private val context: Context) {
    @SuppressLint("ServiceCast")
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // Установить будильник
    fun setAlarm(alarm: AlarmClock) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("alarm_id", alarm.id)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            alarm.timeMillis,
            pendingIntent
        )
    }

    // Удалить будильник
    fun cancelAlarm(alarm: AlarmClock) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}