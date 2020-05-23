package com.mvvm.csquare.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.csquare.CSquareApplication
import kotlinx.coroutines.launch
import com.mvvm.csquare.data.Result
import com.mvvm.csquare.utils.isValidEmail

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val context = application as CSquareApplication
    private val tasksRepository = (context).taskRepository
    val emailText = MutableLiveData<String>("eve.holt@reqres.in")
    val passwordText = MutableLiveData<String>("cityslicka")

    private val flagResponses = MutableLiveData<String>()
    val flagResponse : LiveData<String> = flagResponses

    private val intentFlags = MutableLiveData<Boolean>()
    val intentFlag : LiveData<Boolean> = intentFlags

    fun loginClick() {
        val emailTextString = emailText.value
        val passwordTextString = passwordText.value

        if (passwordTextString == null || emailTextString == null) {
            flagResponses.value = "Not Null"
            return
        }

        if (passwordTextString.isEmpty() || emailTextString.isEmpty()) {
            flagResponses.value = "Not Empty"
            return
        }

        if (!emailTextString.isValidEmail()) {
            flagResponses.value = "Enter Valid Email ID"
            return
        }

        if (context.checkInternet()) {
            viewModelScope.launch {
                var taskResponse = tasksRepository.loginAPICall(emailTextString, passwordTextString)
                if (taskResponse is Result.Success) {
                    intentFlags.value = true
                } else if(taskResponse is Result.Error) {
                    if (taskResponse.errorMessage.isEmpty()){
                        flagResponses.value = "User Data Mismatched"
                        intentFlags.value = false
                    } else {
                        flagResponses.value = taskResponse.errorMessage
                    }
                }
            }
        }
    }
}