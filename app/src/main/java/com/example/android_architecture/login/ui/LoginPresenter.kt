package com.example.android_architecture.login.ui

import com.example.android_architecture.login.ContractLogin
import com.example.android_architecture.login.Interactor.LoginInteractor


class LoginPresenter(private val interactor: ContractLogin.Interactor = LoginInteractor()) :
    ContractLogin.Presenter,
    ContractLogin.CallBack {

   private var view: ContractLogin.View? = null


    override fun setVieww(view: ContractLogin.View) {
        this.view = view
        view.setPresenter(this)
    }

    override fun destroy() {
        view = null
    }


    override fun login(userName: String, password: String) {
        view?.isShowLoader(true)
        interactor.loginProceed(userName, password, this)

    }

    override fun isLoginSuccessfully(isSuccess: Boolean, message: String) {
        view?.isShowLoader(false)
        view?.isLoginSuccessful(isSuccess, message)
    }
}