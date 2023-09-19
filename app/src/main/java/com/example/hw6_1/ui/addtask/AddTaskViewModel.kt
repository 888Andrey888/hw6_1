package com.example.hw6_1.ui.addtask

import androidx.lifecycle.ViewModel
import com.example.hw6_1.model.Model

class AddTaskViewModel : ViewModel() {


    fun saveData(title: String) {
        Model.addDataInList(title)
    }
}