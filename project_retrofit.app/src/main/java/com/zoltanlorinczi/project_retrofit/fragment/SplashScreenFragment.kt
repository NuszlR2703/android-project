package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.LoginViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.TasksViewModelFactory

class SplashScreenFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = TasksViewModelFactory(ThreeTrackerRepository())
        val tasksViewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
        tasksViewModel.isSuccessful.observe(viewLifecycleOwner) {
            if(it == true){
                Thread.sleep(2000)
                findNavController().navigate(R.id.activitiesFragment)
            }
            else{
                Thread.sleep(2000)
                findNavController().navigate(R.id.loginFragment)
            }
        }
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
}