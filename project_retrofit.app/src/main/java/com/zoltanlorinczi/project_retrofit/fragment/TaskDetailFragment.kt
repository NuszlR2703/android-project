package com.zoltanlorinczi.project_retrofit.fragment

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModelFactory

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/12/2021
 */
class TaskDetailFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }



    private lateinit var taskViewModel: TasksViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        taskViewModel = ViewModelProvider(requireActivity(), factory)[TasksViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)

        var title: TextView = view.findViewById(R.id.task_title)
        var description: TextView = view.findViewById(R.id.task_description)
        var deadline: TextView = view.findViewById(R.id.deadline)

        taskViewModel.getTask()
        Log.d(TAG, taskViewModel.activeTaskId.toString())

            title.text = taskViewModel.activeTask?.title.toString()
            description.text = taskViewModel.activeTask?.description.toString()
            deadline.text = taskViewModel.activeTask?.deadline.toString()

        return view
    }


}