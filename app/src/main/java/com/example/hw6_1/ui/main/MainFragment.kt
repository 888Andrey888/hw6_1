package com.example.hw6_1.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hw6_1.R
import com.example.hw6_1.databinding.FragmentMainBinding
import com.example.hw6_1.model.TaskModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val adapter = MainAdapter(this::onClickItem, this::showAlertDialog)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStart()
        initRecyclerView()
        initListener()
    }

    private fun initStart() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setFragmentResultListener("title") { _, bundle ->
            bundle.getString("bundleKey")?.let { mainViewModel.addNewTask(it) }
        }
    }

    private fun initListener() {
        binding.fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.addTaskFragment)
        }
    }

    private fun initRecyclerView() {
        mainViewModel.liveData.observe(viewLifecycleOwner) { list ->
            adapter.addDataInList(list)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun onClickItem(position: Int) {
        mainViewModel.setTaskDone(position)
    }

    private fun showAlertDialog(position: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(getString(R.string.delete))
            .setMessage(getString(R.string.delete_message)).setCancelable(true)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                mainViewModel.deleteTask(position)
            }.setNegativeButton(getString(R.string.no)) { _, _ -> }.show()
    }
}