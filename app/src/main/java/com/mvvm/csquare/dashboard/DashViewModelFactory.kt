package com.mvvm.csquare.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.csquare.data.source.TaskRepository

class DashViewModelFactory(val application: Application, private val repository: TaskRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashBoardFragViewModel(application, repository) as T
    }
}