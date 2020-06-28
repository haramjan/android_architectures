package com.example.android_architecture.base

import com.example.android_architecture.base.Observer

/**
 * @author Irfan ul haq
 * @Note: Threading is avoided for the purpose of complications and understanding
 */

class Observable<T> {
    private val observers = ArrayList<Observer<T>>()
    private val callBack = object : CallBack {

        override fun detach(observer: Observer<*>) {
            observers.remove(observer)
        }
    }

    fun subscribe(observer: Observer<T>): Subscription {
        observers.add(observer)
        return Subscription(observer, callBack)
    }

    fun update(isSuccess: Boolean, stat: T?, e: Throwable?) {

        if (isSuccess)
            notifySuccess(stat!!)
        else
            notifyError(e!!)
    }

    private fun notifySuccess(state: T) {
        for (observer in observers) {
            observer.onSuccess(state)
        }
    }

    private fun notifyError(e: Throwable) {
        for (observer in observers) {
            observer.onError(e)
        }
    }

}


open abstract class Observer<T> {
    abstract fun onSuccess(state: T)
    abstract fun onError(e: Throwable)
}

interface CallBack {
    fun detach(observer: Observer<*>)
}


class Subscription constructor(observer: Observer<*>, callBack: CallBack) {
    private var observer: Observer<*> = observer
    private var callBack: CallBack = callBack

    fun unSubscribe() {
        callBack.detach(observer)
    }

}

