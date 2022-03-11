package com.example.tim

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Generando una entidad para room que corresponde una tabla para la BD
@Entity(tableName = "detail_table")
data class DetailEntity(
    @ColumnInfo(name = "nameDetail") val nameDetail:String = "Jonathan Blandon",
    @ColumnInfo(name = "addressDetail") val addressDetail:String = "Calle 62 #109a-120",
    @ColumnInfo(name = "phoneDetail") val phoneDetail:Number = 3223023259
)