package com.example.remotejobapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.remotejobapp.model.FavoriteJob

@Dao
interface FavotiteDAO {

    @Insert
    suspend fun insertFavorite(favoritejob:FavoriteJob)
    @Delete
    suspend fun deleteFavorite(favoritejob:FavoriteJob)
    @Query(" select * from fav_job ORDER by id DESC ")
    fun SearchFavoritejob():LiveData<FavoriteJob>
}