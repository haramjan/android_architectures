package com.example.android_architecture.login.ui

import com.example.android_architecture.base.Observable
import com.example.android_architecture.base.Observer
import com.example.android_architecture.base.Subscription
import com.example.android_architecture.login.ContractLogin
import com.example.android_architecture.login.Interactor.LoginInteractor
import kotlinx.android.synthetic.main.fragment_login.*
import java.lang.IllegalArgumentException


class LoginViewModel(private val interactor: ContractLogin.Interactor = LoginInteractor()) :
    ContractLogin.ViewModel {

    private var loginObservable = Observable<String>()
    private var subscription: Subscription<String>? = null
    override fun doLogin(userName: String, password: String) {
        if (userName.isEmpty() || password.isEmpty()) {
            loginObservable.update(false,null, IllegalArgumentException( "Ops, userName and password is required"))
            return
        }


        interactor.loginProceed(userName, password)
            .subscribe(object : Observer<String>() {
                override fun onSuccess(state: String) {
                    loginObservable.update(true,state,null)
                }

                override fun onError(e: Throwable) {
                    loginObservable.update(false,null,e)
                }


            })
    }

    override fun getLogin(): Observable<String> {
        return loginObservable
    }


    override fun cleanup() {
        subscription?.unSubscribe()
    }

}