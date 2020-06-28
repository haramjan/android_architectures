package com.example.android_architecture.login.ui

import android.os.Bundle
import com.example.android_architecture.base.BaseActivity
import com.example.android_architecture.R
import com.example.android_architecture.base.addFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }


    fun init() {
        val viewModel =
            LoginViewModel()
        val fragment =
            LoginFragment.newInstance()
        fragment.setViewModel(viewModel)
        this.addFragment(fragment, containerLogin.id)


    }

}