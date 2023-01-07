package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.LoginRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.UserRequestBody
import com.zoltanlorinczi.project_retrofit.api.model.UsersResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<UsersResponse>> = MutableLiveData()
    var product: MutableLiveData<UsersResponse> = MutableLiveData()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                        SharedPreferencesManager.KEY_TOKEN,
                        "Empty token!"
                )
                val response = token?.let {
                    repository.getUsers(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get users response: ${response.body()}")

                    val usersList = response.body()
                    usersList?.let {
                        products.value = usersList
                    }
                } else {
                    Log.d(TAG, "Get users error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "UsersViewModel - getUsers() failed with exception: ${e.message}")
            }
        }
    }

    fun getUser(){
        viewModelScope.launch {
            executeGetMyUser()
        }
    }

    fun updateUser(lastName: String, firstName: String, phone: String, location: String){
        viewModelScope.launch {
            try {

                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {

                    val userRequestBody = UserRequestBody(lastName, firstName, location, phone, "")
                    repository.updateUser(token, userRequestBody)
                }


                if (response?.isSuccessful == true) {

                    Log.d(TAG, "Update user response: ${response.body()}")

                } else {
                    Log.d(TAG, "Update error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "UsersViewModel - updateUser() failed with exception: ${e.message}")
            }
        }
    }


    private fun executeGetMyUser() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getMyUser(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get users response: ${response.body()}")

                    val user = response.body()
                    user?.let {
                        product.value = user
                    }
                } else {
                    Log.d(TAG, "Get user error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "UsersViewModel - getUser() failed with exception: ${e.message}")
            }
        }
    }

}