package com.example.android_architecture.login.ui

import android.os.Bundle
import com.example.android_architecture.BaseActivity
import com.example.android_architecture.R
import com.example.android_architecture.addFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }


    fun init(){
        val fragment = LoginFragment.newInstance()
        this.addFragment(fragment,containerLogin.id)
        val presenter =
            LoginPresenter()
        presenter.setVieww(fragment)
    }

}