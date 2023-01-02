package com.example.remotejobapp.api

import com.example.remotejobapp.model.RemoteJobsREsponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("remote-jobs")
    suspend fun getAllJobs():Response<RemoteJobsREsponse>

    @GET("remote-jobs")
    suspend fun getSearchJobs(
        @Query("search") search:String
    ):Response<RemoteJobsREsponse>
}