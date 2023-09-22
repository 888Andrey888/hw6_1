package com.example.hw6_1.presentation.addtask

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.hw6_1.MainActivity
import com.example.hw6_1.R
import com.example.hw6_1.databinding.FragmentAddTaskBinding
import com.example.hw6_1.model.TaskModel
import com.example.hw6_1.utils.Сonstants.Companion.REQUIRES_KEY_ADD_TASK
import com.example.hw6_1.utils.Сonstants.Companion.REQUIRES_KEY_ADD_TO_MAIN
import com.example.hw6_1.utils.Сonstants.Companion.REQUIRES_KEY_MAIN_TO_ADD
import com.example.hw6_1.utils.Сonstants.Companion.REQUIRES_KEY_SET_TASK
import com.example.hw6_1.utils.Сonstants.Companion.REQUIRES_KEY_UPDATE_TASK

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private var _task: TaskModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener(REQUIRES_KEY_MAIN_TO_ADD) { _, bundle ->
            bundle.getSerializable(REQUIRES_KEY_SET_TASK)?.let { task ->
                _task = task as TaskModel
                initView()
            }
        }
        initListener()
    }


    override fun onStart() {
        super.onStart()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        _task?.let { task ->
            binding.btnAddTask.text = getString(R.string.update)
            binding.etTitle.setText(task.title)
            binding.cbState.visibility = View.VISIBLE
            binding.cbState.isChecked = task.state
        }
    }

    private fun initListener() {
        binding.btnAddTask.setOnClickListener {
            if (_task == null)
                addNewTask()
            else {
                _task!!.title = binding.etTitle.text.toString()
                _task!!.state = binding.cbState.isChecked
                updateTask()
            }
        }
    }

    private fun updateTask() {
        setFragmentResult(
            REQUIRES_KEY_ADD_TO_MAIN,
            bundleOf(REQUIRES_KEY_UPDATE_TASK to _task)
        )
        findNavController().navigateUp()
    }

    private fun addNewTask() {
        setFragmentResult(
            REQUIRES_KEY_ADD_TO_MAIN,
            bundleOf(REQUIRES_KEY_ADD_TASK to binding.etTitle.text.toString())
        )
        findNavController().navigateUp()
    }

    @SuppressLint("RestrictedApi")
    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.openOptionsMenu()
    }
}