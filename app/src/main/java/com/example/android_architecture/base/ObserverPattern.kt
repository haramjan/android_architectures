package com.example.android_architecture.base

import com.example.android_architecture.base.Observer

 class Observable<T> {
    private val observers = ArrayList<Observer<T>>()
     private val callBack = object : CallBack<T> {

         override fun detach(observer: Observer<T>) {
          observers.remove(observer)
         }
     }

     fun subscribe(observer: Observer<T>): Subscription<T>{
        observers.add(observer)
        return Subscription<T>(observer,callBack)
    }

    fun update(isSuccess:Boolean,stat:T?, e:Throwable?){

        if(isSuccess)
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


open abstract class Observer<T>{
    abstract fun onSuccess(state:T)
    abstract fun onError(e:Throwable)
}



interface CallBack<T>{
    fun detach(observer: Observer<T>)
}



class Subscription<T>  constructor(observer: Observer<T>, callBack: CallBack<T>) {
    private var observer: Observer<T> = observer
    private var callBack: CallBack<T> = callBack

    fun unSubscribe() {
        callBack.detach(observer)
    }

}


