package com.mvvm.csquare.data.source

import androidx.lifecycle.LiveData
import com.mvvm.csquare.data.source.model.RealestateResponse
import com.mvvm.csquare.data.Result
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.data.source.model.LoginResponse

interface TaskRepository {

    //API Implementation
    suspend fun getAllData(): Result<List<DataUserList>>

    //Room Implementation
    fun observeTask(): LiveData<Result<List<DataUserList>>>

    suspend fun getTasks(): Result<List<DataUserList>>

    suspend fun saveTaskAll(task: List<DataUserList>)

    suspend fun saveTask(task: DataUserList)

    suspend fun loginAPICall(email: String, password: String) : Result<LoginResponse>

}