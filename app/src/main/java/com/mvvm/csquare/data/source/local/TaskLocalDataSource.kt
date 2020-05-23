package com.mvvm.csquare.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mvvm.csquare.data.source.model.RealestateResponse
import com.mvvm.csquare.data.Result
import com.mvvm.csquare.data.source.model.DataUserList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TaskLocalDataSource internal constructor(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TaskDataSource {

    override fun observeTasks(): LiveData<Result<List<DataUserList>>> {
        return taskDao.observeTask().map {
            Result.Success(it)
        }
    }

    override suspend fun getTasks(): Result<List<DataUserList>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(taskDao.getTask())
        } catch (e: Exception) {
            Result.Error("DataBase Error", e.message!!)
        }
    }

    override suspend fun saveTaskAll(task: List<DataUserList>) {
        taskDao.saveAllTask(task)
    }

    override suspend fun saveTask(task: DataUserList) {
        taskDao.insertTask(task)
    }
}