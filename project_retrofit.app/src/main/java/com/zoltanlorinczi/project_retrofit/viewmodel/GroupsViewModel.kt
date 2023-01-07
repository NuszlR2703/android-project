package com.zoltanlorinczi.project_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoltanlorinczi.project_retrofit.App
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.GroupsResponse
import com.zoltanlorinczi.project_retrofit.api.model.UsersResponse
import com.zoltanlorinczi.project_retrofit.manager.SharedPreferencesManager
import kotlinx.coroutines.launch



class GroupsViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var products: MutableLiveData<List<GroupsResponse>> = MutableLiveData()
    var userProducts: MutableLiveData<List<UsersResponse>> = MutableLiveData()
    var groupId: Int=0
    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData();



    init {
        getGroups()
    }

    private fun getGroups() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                        SharedPreferencesManager.KEY_TOKEN,
                        "Empty token!"
                )
                val response = token?.let {
                    repository.getGroups(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get groups response: ${response.body()}")

                    val groupsList = response.body()
                    groupsList?.let {
                        products.value = groupsList
                    }
                    getUsers()

                } else {
                    Log.d(TAG, "Get groups error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "GroupsViewModel - getGroups() failed with exception: ${e.message}")
            }
        }
    }

    fun getUsers(){
        executeGetUsers();
    }

    private fun executeGetUsers() {
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
                    isSuccessful.value=true;
                    Log.d(UsersViewModel.TAG, "Get users response: ${response.body()}")

                    val usersList = response.body()
                    usersList?.let {
                        userProducts.value = usersList.filter { it.departmentID == groupId }
                    }
                } else {
                    isSuccessful.value=false;
                    Log.d(UsersViewModel.TAG, "Get users error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(UsersViewModel.TAG, "UsersViewModel - getUsers() failed with exception: ${e.message}")
            }
        }
    }
}