package com.example.tim

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DetailEntity::class], version = 2, exportSchema = false)
abstract class DetailDatabase : RoomDatabase() {
    abstract fun detailDao(): DetailDao
}