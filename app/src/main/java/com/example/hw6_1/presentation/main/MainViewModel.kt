package com.example.hw6_1.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw6_1.model.TaskModel
import com.example.hw6_1.utils.ToDisplay

class MainViewModel : ViewModel() {

    private var data = mutableListOf<TaskModel>()
    private var _sort = ToDisplay.ALL
    private val _liveData = MutableLiveData<MutableList<TaskModel>>(mutableListOf())
    val liveData: LiveData<MutableList<TaskModel>>
        get() = _liveData

    fun setSortList(sort: ToDisplay) {
        _sort = sort
        setDateToDisplay()
    }

    private fun setDateToDisplay() {
        when (_sort) {
            ToDisplay.ACTIVE -> showActive()
            ToDisplay.COMPLETED -> showCompleted()
            else -> showAll()
        }
    }

    fun addNewTask(title: String) {
        data.add(TaskModel(id = data.size, title = title))
        setDateToDisplay()
    }

    fun setTaskDone(task: TaskModel) {
        data[task.id].state = true
        setDateToDisplay()
    }

    fun deleteTask(task: TaskModel) {
        data.removeAt(task.id)
        setDateToDisplay()
    }

    fun updateTask(task: TaskModel) {
        deleteTask(task)
        data.add(task.id, task)
        setDateToDisplay()
    }

    private fun showAll() {
        _liveData.value = data
    }

    private fun showActive() {
        val sortedList = mutableListOf<TaskModel>()
        data.forEach { task ->
            if (!task.state)
                sortedList.add(task)
        }
        _liveData.value = sortedList
    }

    private fun showCompleted() {
        val sortedList = mutableListOf<TaskModel>()
        data.forEach { task ->
            if (task.state)
                sortedList.add(task)
        }
        _liveData.value = sortedList
    }
}