package com.example.remotejobapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remotejobapp.model.RemoteJobsREsponse
import com.example.remotejobapp.repo.RemoteJobsRepository
import com.example.remotejobapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RemotejobViewModel @Inject constructor(private val repo:RemoteJobsRepository) :ViewModel(){

    val jobresponse=MutableLiveData<Resource<RemoteJobsREsponse>>()

    fun getAlljobs()=viewModelScope.launch(Dispatchers.IO) {

        jobresponse.postValue(Resource.Loading())
        val response=repo.getAllJobs()
        jobresponse.postValue(handleJobs(response))
    }

    private fun handleJobs(response: Response<RemoteJobsREsponse>): Resource<RemoteJobsREsponse>? {
        if(response.isSuccessful){
            response.body()?.let {
               return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}