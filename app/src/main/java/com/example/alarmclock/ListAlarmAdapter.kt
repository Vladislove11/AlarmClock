package com.example.alarmclock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.MutableLiveData

class ListAlarmAdapter(context: Context, alarmList: MutableList <AlarmClock>): ArrayAdapter<AlarmClock>(
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

        val nameAlarmLV = view?.findViewById<TextView>(R.id.nameAlarmTV)
        val switchCompatSC = view?.findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.switchCompatSC)

        nameAlarmLV?.text = alarmClock?.timeFormat
        if (alarmClock != null) {
            switchCompatSC?.isChecked = alarmClock.switch
        }else{
            switchCompatSC?.isChecked = false
        }

        return  view!!
    }
}