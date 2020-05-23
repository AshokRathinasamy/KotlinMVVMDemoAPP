package com.mvvm.csquare.data.source.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class RealestateResponse constructor(
    val ad: Ad,
    val `data`: List<DataUserList>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)

data class Ad constructor(
    val company: String,
    val text: String,
    val url: String
)

@Entity(tableName = "tasks")
@Parcelize
data class DataUserList constructor(
    val avatar: String,
    val email: String,
    val first_name: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val last_name: String
) : Parcelable