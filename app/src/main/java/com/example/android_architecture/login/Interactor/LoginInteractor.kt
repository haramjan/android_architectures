package com.example.android_architecture.login.Interactor

import android.app.job.JobScheduler
import android.os.Handler
import com.example.android_architecture.base.Observable
import com.example.android_architecture.login.ContractLogin
import java.lang.IllegalArgumentException

/**
 * Login business model similar to use case
 * @author irfan Ul haq
 */

class LoginInteractor(private val observable: Observable<String> = Observable<String>()) :
    ContractLogin.Interactor {

    // A database or repository pattern can be used in more ideal situation
    val userName = "irfan"
    val password = "123"


    override fun loginProceed(
        userName: String,
        password: String
    ): Observable<String>{

        val handler = Handler()
        handler.postDelayed(Runnable {
            if (userName.trim().toLowerCase() == this.userName && password.trim().toLowerCase() == this.password)
                observable.update(true,"Login is successful",null)
            else
                observable.update(false,null,IllegalArgumentException("Login is not successful"))
        }, 5000)

        return observable
    }

}