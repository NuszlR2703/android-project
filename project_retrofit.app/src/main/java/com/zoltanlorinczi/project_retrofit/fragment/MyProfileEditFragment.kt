package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.UserRequestBody
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModelFactory

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/12/2021
 */
class MyProfileEditFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_my_profile_edit, container, false)

        var lastName: EditText = view.findViewById(R.id.lastName)
        var firstName: EditText = view.findViewById(R.id.firstName)
        var email: TextView = view.findViewById(R.id.email)
        var phone: EditText = view.findViewById(R.id.phone)
        var location: EditText = view.findViewById(R.id.location)

        val save_button: Button = view.findViewById(R.id.save_button)

        userViewModel.getUser();

        userViewModel.product.observe(this.viewLifecycleOwner) {
            lastName.setText(it.lastName)
            firstName.setText(it.firstName)
            email.text = it.emailAddress
            phone.setText(it.phoneNumber)
            location.setText(it.location)
        }

        save_button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                userViewModel.updateUser(lastName.text.toString(), firstName.text.toString(), phone.text.toString(),location.text.toString())
                findNavController().navigate(R.id.myProfileFragment)
            }
        })

        return view
    }
}