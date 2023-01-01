package com.example.remotejobapp.repo

import com.example.remotejobapp.db.FavotiteDAO
import com.example.remotejobapp.model.FavoriteJob
import javax.inject.Inject

class RemoteJobFavoriteRepo @Inject constructor(private val dao:FavotiteDAO) {

    suspend fun insert(favoriteJob: FavoriteJob)=dao.insertFavorite(favoriteJob)
    suspend fun delete(favoriteJob: FavoriteJob)=dao.deleteFavorite(favoriteJob)
    fun getAllAavoriteJobs()=dao.SearchFavoritejob()
}
