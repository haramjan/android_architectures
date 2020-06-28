package com.example.android_architecture.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_architecture.base.BaseFragment
import com.example.android_architecture.R
import com.example.android_architecture.base.Observable
import com.example.android_architecture.base.Observer
import com.example.android_architecture.base.Subscription
import com.example.android_architecture.login.ContractLogin
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment(), ContractLogin.View {

    private var viewModel: ContractLogin.ViewModel? = null
    private var subscription: Subscription? = null

    //fragmentMethods
    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin() {
        var message = ""
        val userName = etUserName.text.toString()
        val password = etPassword.text.toString()
        message = "Login is in progress..."
        tvMessageLogin.text = message
        isShowLoader(true)
        viewModel?.doLogin(userName, password)

    }


    override fun onDestroy() {
        subscription?.unSubscribe()
        this.viewModel?.cleanup()
        subscription = null
        this.viewModel = null
        super.onDestroy()
    }

    // ends of fragmentMethods

    override fun setViewModel(viewModel: ContractLogin.ViewModel) {
        this.viewModel = viewModel
        subscription = this.viewModel!!.getLogin().subscribe(
            object : Observer<String>() {
                override fun onSuccess(state: String) {
                    isShowLoader(false)
                    isLoginSuccessful(true, state)
                }

                override fun onError(e: Throwable) {
                    isShowLoader(false)
                    isLoginSuccessful(false, e.message!!)
                }

            }
        )
    }


    override fun isShowLoader(isShow: Boolean) {
        pbLogin.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun isLoginSuccessful(boolean: Boolean, message: String) {
        tvMessageLogin.text = message
    }
}