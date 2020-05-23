package com.mvvm.csquare.data.source

import androidx.lifecycle.LiveData
import com.mvvm.csquare.data.Result
import com.mvvm.csquare.data.source.local.TaskDataSource
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.data.source.model.LoginResponse
import com.mvvm.csquare.data.source.model.RealestateResponse
import com.mvvm.csquare.data.source.remote.ApiCall
import com.mvvm.csquare.data.source.remote.SafeApiRequest
import org.json.JSONObject

class DefaultTaskRepository(
    private val apiCall: ApiCall,
    private val roomDb: TaskDataSource): SafeApiRequest(), TaskRepository {

    override suspend fun getAllData(): Result<List<DataUserList>> {
        var taskList = apiRequest { apiCall.getUserData() }
        if (taskList is Result.Success){
            saveTaskAll(taskList.data.data)
        }
        return roomDb.getTasks()
    }

    override fun observeTask(): LiveData<Result<List<DataUserList>>> {
        return roomDb.observeTasks()
    }

    override suspend fun getTasks(): Result<List<DataUserList>> {
        return roomDb.getTasks()
    }

    override suspend fun saveTaskAll(task: List<DataUserList>) {
        return roomDb.saveTaskAll(task)
    }

    override suspend fun saveTask(task: DataUserList) {
        roomDb.saveTask(task)
    }

    override suspend fun loginAPICall(email: String, password: String) : Result<LoginResponse> {
        val hashMap = HashMap<String, String>()
        hashMap.set("email", email)
        hashMap.set("password", password)
        return apiRequest { apiCall.loginUser(hashMap) }
    }
}