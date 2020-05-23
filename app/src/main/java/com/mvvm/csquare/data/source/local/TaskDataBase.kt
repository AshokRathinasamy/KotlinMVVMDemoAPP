package com.mvvm.csquare.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.data.source.model.RealestateResponse

@Database(entities = [DataUserList::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}