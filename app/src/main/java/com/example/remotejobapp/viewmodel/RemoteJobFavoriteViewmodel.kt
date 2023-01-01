package com.example.remotejobapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remotejobapp.model.FavoriteJob
import com.example.remotejobapp.repo.RemoteJobFavoriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteJobFavoriteViewmodel @Inject constructor(private val repo:RemoteJobFavoriteRepo):ViewModel() {



    fun insert(favoriteJob: FavoriteJob)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(favoriteJob)
    }

    fun delete(favoriteJob: FavoriteJob)=viewModelScope.launch(Dispatchers.IO) {
        repo.delete(favoriteJob)
    }

    val allFavoriteJob: LiveData<FavoriteJob> = repo.getAllAavoriteJobs()
}