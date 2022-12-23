package com.example.notebook.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    var id :Int ?= null,
    var title: String,
    var notes : String,
    var date_time :String,
    var category :String

): Parcelable



