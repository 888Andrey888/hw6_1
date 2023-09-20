package com.example.hw6_1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw6_1.model.TaskModel

class MainViewModel : ViewModel() {

    private var data = mutableListOf<TaskModel>()
    private val _liveData = MutableLiveData<MutableList<TaskModel>>(mutableListOf())
    val liveData: LiveData<List<TaskModel>>
        get() = _liveData as LiveData<List<TaskModel>>

    fun addNewTask(title: String){
        data.add(TaskModel(title = title))
        _liveData.value = data
    }

    fun setTaskDone(position: Int) {
        data[position].state = true
        _liveData.value = data
    }

    fun deleteTask(position: Int) {
        data.removeAt(position)
        _liveData.value = data
    }
}