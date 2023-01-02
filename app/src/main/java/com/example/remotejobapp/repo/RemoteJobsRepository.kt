package com.example.remotejobapp.repo

import com.example.remotejobapp.api.ApiServices
import javax.inject.Inject


class RemoteJobsRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getAllJobs()=apiServices.getAllJobs()
    suspend fun getSearchJobs(search:String)=apiServices.getSearchJobs(search)
}