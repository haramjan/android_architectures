package com.example.android_architecture.login

interface ContractLogin {

    interface View {
        fun setPresenter(presenter: Presenter)
        fun isShowLoader(boolean: Boolean)
        fun isLoginSuccessful(boolean: Boolean, message:String)
    }

    interface Presenter {
        fun setVieww(view: View)
        fun destroy()
        fun login(userName: String, password: String)
    }

    interface Interactor{
        fun loginProceed(userName: String, password: String,callBack: CallBack)
    }

    interface CallBack{
        fun isLoginSuccessfully(isSuccess: Boolean, message:String)
    }
}