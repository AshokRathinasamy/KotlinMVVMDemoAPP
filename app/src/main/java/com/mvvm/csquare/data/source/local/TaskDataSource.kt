package com.mvvm.csquare.data.source.local

import androidx.lifecycle.LiveData
import com.mvvm.csquare.data.source.model.RealestateResponse
import com.mvvm.csquare.data.Result
import com.mvvm.csquare.data.source.model.DataUserList

interface TaskDataSource {
    fun observeTasks() : LiveData<Result<List<DataUserList>>>

    suspend fun getTasks(): Result<List<DataUserList>>

    suspend fun saveTaskAll(task: List<DataUserList>)

    suspend fun saveTask(task: DataUserList)

}