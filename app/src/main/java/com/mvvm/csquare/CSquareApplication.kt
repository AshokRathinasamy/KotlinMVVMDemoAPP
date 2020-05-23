package com.mvvm.csquare

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.mvvm.csquare.data.source.TaskRepository
import com.mvvm.csquare.utils.ServiceLocator

class CSquareApplication : Application() {

    val taskRepository: TaskRepository
        get() = ServiceLocator.provideTasksRepository(this)


    fun checkInternet(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

}