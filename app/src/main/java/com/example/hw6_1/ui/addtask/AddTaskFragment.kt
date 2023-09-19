package com.example.hw6_1.ui.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hw6_1.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var addTaskViewModel: AddTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTaskViewModel = ViewModelProvider(this)[AddTaskViewModel::class.java]
        initListener()
    }

    private fun initListener() {
        binding.btnAddTask.setOnClickListener {
            addTaskViewModel.saveData(binding.etTitle.text.toString())
            findNavController().navigateUp()
        }
    }
}