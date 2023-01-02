package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.UsersResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class UsersViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<UsersResponse>> = MutableLiveData()

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

}