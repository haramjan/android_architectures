package com.example.android_architecture.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_architecture.BaseFragment
import com.example.android_architecture.R
import kotlinx.android.synthetic.main.fragment_login.*


    class LoginFragment : BaseFragment(), ContractLogin.View {


        private  var presenter: ContractLogin.Presenter? = null

        //fragmentMethods
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
        if (userName.isEmpty() || password.isEmpty()) {
            message = "Ops, userName and password is required"
            tvMessageLogin.text = message
            return
        }

        message = "Login is in progress..."
        tvMessageLogin.text = message
        presenter?.login(userName, password)


    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    override fun onDestroy() {
        this.presenter = null
        super.onDestroy()
    }

    // ends of fragmentMethods


    //MVP methods
    override fun setPresenter(presenter: ContractLogin.Presenter) {
        this.presenter = presenter
    }

    override fun isShowLoader(isShow: Boolean) {
        pbLogin.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun isLoginSuccessful(boolean: Boolean, message: String) {
        tvMessageLogin.text = message
    }

    // end of MVP methods
}