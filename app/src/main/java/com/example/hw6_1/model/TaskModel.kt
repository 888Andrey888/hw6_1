package com.example.hw6_1.model

import java.io.Serializable

data class TaskModel(
    var id: Int,
    var title: String,
    var state: Boolean = false
) : Serializable
