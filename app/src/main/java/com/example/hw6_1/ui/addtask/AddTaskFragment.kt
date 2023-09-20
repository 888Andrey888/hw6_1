package com.example.hw6_1.ui.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hw6_1.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btnAddTask.setOnClickListener {
            setFragmentResult("title", bundleOf("bundleKey" to binding.etTitle.text.toString()))
            findNavController().navigateUp()
        }
    }
}