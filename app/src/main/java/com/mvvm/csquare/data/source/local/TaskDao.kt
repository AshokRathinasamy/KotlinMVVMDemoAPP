package com.mvvm.csquare.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.data.source.model.RealestateResponse

@Dao
interface TaskDao {

    @Query("Select * From Tasks")
    fun observeTask(): LiveData<List<DataUserList>>

    @Query("Select * From Tasks")
    suspend fun getTask(): List<DataUserList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : DataUserList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllTask(task: List<DataUserList>)

}