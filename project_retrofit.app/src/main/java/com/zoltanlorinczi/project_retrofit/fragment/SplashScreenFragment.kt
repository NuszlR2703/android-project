package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.*

class SplashScreenFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var groupsViewModel: GroupsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = GroupsViewModelFactory(ThreeTrackerRepository())
        groupsViewModel = ViewModelProvider(requireActivity(), factory)[GroupsViewModel::class.java]
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupsViewModel.getUsers();
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavBar)
        navBar?.visibility = View.GONE;
        groupsViewModel.isSuccessful.observe(viewLifecycleOwner) {
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