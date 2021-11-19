package com.example.notesapp.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val title:String,
    val note:String


)
