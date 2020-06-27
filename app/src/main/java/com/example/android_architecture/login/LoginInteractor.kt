package com.example.android_architecture.login

import android.os.Handler

/**
 * Login business model similar to use case
 * @author irfan Ul haq
 */

class LoginInteractor() : ContractLogin.Interactor {

    // A database or repository pattern can be used in more ideal situation
    val userName = "irfan"
    val password = "123"


    override fun loginProceed(
        userName: String,
        password: String,
        callBack: ContractLogin.CallBack
    ) {
        val handler = Handler()
        handler.postDelayed(Runnable {
            if (userName.trim().toLowerCase() == this.userName && password.trim().toLowerCase() == this.password)
                callBack.isLoginSuccessfully(true, "Login is successful")
            else
                callBack.isLoginSuccessfully(false, "Login is not successful")
        }, 5000)
    }

}