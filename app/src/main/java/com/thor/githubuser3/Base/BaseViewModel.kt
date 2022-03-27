package com.thor.githubuser3.Base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    protected fun Disposable.disposeOnCleared(): Disposable {
        disposables.add(this)
        return this
    }
}