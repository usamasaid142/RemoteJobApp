package com.example.remotejobapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class RemoteJobsREsponse(
    @SerializedName("job-count")
    val jobCount: Int,
    @SerializedName("jobs")
    val jobs: List<Job>?,
    @SerializedName("0-legal-notice")
    val legalNotice: String,
    @SerializedName("00-warning")
    val warning: String
):Parcelable

@Parcelize
data class Job(
    @SerializedName("candidate_required_location")
    val candidateRequiredLocation: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("company_logo")
    val companyLogo: String?,
    @SerializedName("company_logo_url")
    val companyLogoUrl: String?,
    @SerializedName("company_name")
    val companyName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("job_type")
    val jobType: String?,
    @SerializedName("publication_date")
    val publicationDate: String?,
    @SerializedName("salary")
    val salary: String?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
):Parcelable