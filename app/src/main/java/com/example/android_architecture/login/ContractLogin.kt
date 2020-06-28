package com.example.android_architecture.login

import com.example.android_architecture.base.Observable

interface ContractLogin {

    interface View {
        // just helper methods
        fun setViewModel(viewModel: ViewModel)
        fun isShowLoader(boolean: Boolean)
        fun isLoginSuccessful(boolean: Boolean, message:String)
    }

    interface ViewModel {
        fun doLogin(userName: String, password: String)
        fun getLogin():Observable<String>
        fun cleanup()
    }

    interface Interactor{
        fun loginProceed(userName: String, password: String): Observable<String>
    }
}