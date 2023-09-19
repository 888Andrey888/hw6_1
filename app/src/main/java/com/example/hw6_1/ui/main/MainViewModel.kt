package com.example.hw6_1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw6_1.model.Model
import com.example.hw6_1.model.TaskModel

class MainViewModel : ViewModel() {

    private var _liveData = MutableLiveData(Model.list)
    val liveData: LiveData<ArrayList<TaskModel>>
        get() = _liveData

    fun setDone(position: Int) {
        Model.setDone(position)
        _liveData.value = Model.list
    }

    fun deleteTask(position: Int) {
        Model.deleteTask(position)
        _liveData.value = Model.list
    }
}