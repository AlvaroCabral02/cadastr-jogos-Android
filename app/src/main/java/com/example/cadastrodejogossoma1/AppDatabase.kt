package com.example.cadastrodejogossoma1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Artesanato::class], version = 1)
abstract class AppDatabase : RoomDatabase (){
    abstract fun artesanatoDao(): ArtesanatoDao
}