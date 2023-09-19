package com.example.hw6_1.model

class Model {
    companion object {
        private var arrayList = arrayListOf<TaskModel>()
        val list: ArrayList<TaskModel>
            get() = arrayList

        fun addDataInList(title: String) {
            arrayList.add(TaskModel(title))
        }

        fun setDone(position: Int){
            arrayList[position].state = true
        }

        fun deleteTask(position: Int) {
            arrayList.removeAt(position)
        }
    }
}