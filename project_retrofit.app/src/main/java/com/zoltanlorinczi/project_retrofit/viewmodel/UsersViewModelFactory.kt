package com.zoltanlorinczi.project_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository


class UsersViewModelFactory(private val repository: ThreeTrackerRepository) : Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(repository) as T
    }
}