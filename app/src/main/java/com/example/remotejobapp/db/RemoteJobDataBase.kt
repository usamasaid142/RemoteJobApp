package com.example.remotejobapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remotejobapp.model.FavoriteJob

@Database(entities = [FavoriteJob::class], version = 1)
abstract class RemoteJobDataBase:RoomDatabase() {

    abstract fun favJobDao():FavotiteDAO
}