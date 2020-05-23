package com.mvvm.csquare.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mvvm.csquare.R
import com.mvvm.csquare.dashboard.DashBoardActivity
import com.mvvm.csquare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

//    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var viewDataBinding: ActivityLoginBinding
    val userModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewDataBinding.viewmodel = userModel
        viewDataBinding.lifecycleOwner = this

        validationResonse()
        intentActivity()
    }

    fun validationResonse(){
        userModel.flagResponse.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    fun intentActivity(){
        userModel.intentFlag.observe(this, Observer { flag ->
            if (flag){
                startActivity(Intent(this, DashBoardActivity::class.java))
                finish()
            }
        })
    }
}
