package com.example.alarmclock

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {
    val listAlarm: MutableList<AlarmClock> = mutableListOf()
    val listAlarmData: MutableLiveData<AlarmClock> by lazy { MutableLiveData<AlarmClock>() }
}