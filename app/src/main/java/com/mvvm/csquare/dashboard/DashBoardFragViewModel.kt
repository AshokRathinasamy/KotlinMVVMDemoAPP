package com.mvvm.csquare.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.mvvm.csquare.CSquareApplication
import com.mvvm.csquare.data.source.TaskRepository
import kotlinx.coroutines.launch
import com.mvvm.csquare.data.Result.Success
import com.mvvm.csquare.data.source.model.DataUserList
import com.mvvm.csquare.utils.Event
import com.mvvm.csquare.utils.isConnectedToNetwork

class DashBoardFragViewModel(private val context: Application, private val repository: TaskRepository) : ViewModel() {

    private val _intentEvent = MutableLiveData<Event<DataUserList>>()
    val intentEvent: LiveData<Event<DataUserList>> = _intentEvent

    private val _listCount = MutableLiveData<Int>()
    val listCount: LiveData<Int> = _listCount

    private val _realEstateList = repository.observeTask().switchMap {
        val listData = MutableLiveData<List<DataUserList>>()
        when (it) {
            is Success -> listData.value = it.data
            else -> listData.value = emptyList()
        }
        _listCount.value = listData.value?.size
        listData
    }
    val realEstateList: LiveData<List<DataUserList>> = _realEstateList

    init {
        getRealEstateData()
    }

    private fun getRealEstateData() {
        if ((context as CSquareApplication).checkInternet()) {
            viewModelScope.launch {
                repository.getAllData()
            }
        }
    }

    fun onItemClicked(userData : DataUserList){
        _intentEvent.value = Event(userData)
    }
}