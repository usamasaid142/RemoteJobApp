package com.example.remotejobapp.api

import com.example.remotejobapp.model.RemoteJobsREsponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("remote-jobs")
    suspend fun getAllJobs():Response<RemoteJobsREsponse>
}