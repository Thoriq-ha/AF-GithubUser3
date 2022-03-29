package com.thor.githubuser3.UI.ViewModel

import androidx.lifecycle.MutableLiveData
import com.thor.githubuser3.Base.BaseViewModel
import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.UI.Page.Home.HomeMethod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class HomeViewModel(private val method: HomeMethod) : BaseViewModel() {

    private val _stateList = MutableLiveData<HomeState>()

    val stateList get() = _stateList

    private val subscribeSearch: PublishSubject<String> = PublishSubject.create()

    init {
        getListUser()

        subscribeSearch
            .debounce(800, TimeUnit.MILLISECONDS)
            .distinct()
            .filter { text -> text.isNotBlank() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { getSearch(it) }.disposeOnCleared()
    }

    fun refresh() {
        getListUser()
    }

    private fun getListUser() {
        method.list()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _stateList.postValue(HomeState.OnLoading)
            }.subscribe({
                _stateList.postValue(HomeState.OnSuccess(it))
            }, {
                _stateList.postValue(HomeState.OnError(it?.message ?: "Terjadi Kesalahan"))
            }).disposeOnCleared()
    }

    fun search(keyword: String) {
        subscribeSearch.onNext(keyword)
    }


    private fun getSearch(keyword: String) {
        method.search(keyword)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _stateList.postValue(HomeState.OnLoading)
            }.subscribe({
                _stateList.postValue(HomeState.OnSuccess(it))
            }, {
                _stateList.postValue(HomeState.OnError(it?.message ?: "Terjadi Kesalahan"))
            }).disposeOnCleared()
    }


}

sealed class HomeState {
    object OnLoading : HomeState()
    data class OnSuccess(val list: List<User>) : HomeState()
    data class OnError(val message: String) : HomeState()
}