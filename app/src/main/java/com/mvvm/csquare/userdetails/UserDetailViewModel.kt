package com.mvvm.csquare.userdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.csquare.CSquareApplication
import com.mvvm.csquare.data.source.model.DataUserList

class UserDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _userImage = MutableLiveData<String>()
    val userImage: LiveData<String> = _userImage

    private val _userFirstName = MutableLiveData<String>()
    val userFirstname: LiveData<String> = _userFirstName

    private val _userLastName = MutableLiveData<String>()
    val userLastName: LiveData<String> = _userLastName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    fun setUserDetails(userData: DataUserList) {
        _userImage.value = userData.avatar
        _userFirstName.value = userData.first_name
        _userLastName.value = userData.last_name
        _userEmail.value = userData.email
    }
}