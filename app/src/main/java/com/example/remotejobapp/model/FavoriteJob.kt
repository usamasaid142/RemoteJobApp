package com.example.remotejobapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_job")
data class FavoriteJob(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val candidateRequiredLocation: String?,
    val category: String?,
    val companyLogo: String?,
    val companyLogoUrl: String?,
    val companyName: String?,
    val description: String?,
    val jobId: Int?,
    val jobType: String?,
    val publicationDate: String?,
    val salary: String?,
    val tags: List<String>?,
    val title: String?,
    val url: String?
)