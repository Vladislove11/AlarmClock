package com.example.alarmclock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity.RESULT_OK

class AlarmReceiver : BroadcastReceiver() {
    companion object{
        var ringtone: Ringtone? = null
    }
    //var ringtone: Ringtone? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        var notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(context, notificationUri)
        if (ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(context, notificationUri)
        }
        if (ringtone != null){
            ringtone!!.play()
        }
        val intent = Intent(context!!.applicationContext, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}