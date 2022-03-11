package com.example.tim

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DetailDao {

    @Query("SELECT * FROM detail_table")
    fun getAll(): List<DetailEntity>

    @Query("SELECT nameDetail FROM detail_table")
    fun getName() : DetailEntity

    @Insert
    fun insertDetail(details:DetailEntity)
}