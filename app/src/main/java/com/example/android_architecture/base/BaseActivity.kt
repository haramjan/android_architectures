package com.example.android_architecture.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {



    // use this for common drawer and other common functionality w.r.t activity
}







//Extention functions

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    containerId: Int,
    addToBackStack: Boolean = false
) {
    val fragManager = this.supportFragmentManager
    val transaction = fragManager.beginTransaction()

    if (addToBackStack)
        transaction.addToBackStack(fragment::class.java.simpleName)

    transaction
        .add(containerId, fragment)
        .commit()
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    containerId: Int,
    addToBackStack: Boolean = false
) {
    val fragManager = this.supportFragmentManager
    val transaction = fragManager.beginTransaction()

    if (addToBackStack)
        transaction.addToBackStack(fragment::class.java.simpleName)

    transaction
        .replace(containerId, fragment)
        .commit()
}