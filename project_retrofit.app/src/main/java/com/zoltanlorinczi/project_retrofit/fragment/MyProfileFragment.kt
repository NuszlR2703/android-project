package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModelFactory

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/12/2021
 */
class MyProfileFragment : Fragment() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }



    private lateinit var userViewModel: UsersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = UsersViewModelFactory(ThreeTrackerRepository())
        userViewModel = ViewModelProvider(this, factory)[UsersViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_profile, container, false)

        var name: TextView = view.findViewById(R.id.firstName)
        var email: TextView = view.findViewById(R.id.email)
        var phone: TextView = view.findViewById(R.id.phone)
        var location: TextView = view.findViewById(R.id.location)

        val edit_button: Button = view.findViewById(R.id.edit_button)

        userViewModel.getUser()

        edit_button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                findNavController().navigate(R.id.myProfileEditFragment)
            }
        })






        userViewModel.product.observe(this.viewLifecycleOwner) {
            name.text = it.firstName.plus(" ").plus(it.lastName)
            email.text = it.emailAddress
            phone.text = it.phoneNumber
            location.text = it.location
        }

        return view
    }


}