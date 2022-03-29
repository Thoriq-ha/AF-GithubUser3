package com.thor.githubuser3.UI.ViewModel

import androidx.lifecycle.MutableLiveData
import com.thor.githubuser3.Base.BaseViewModel
import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.UI.Page.Favorite.FavoriteMethod
import io.reactivex.schedulers.Schedulers


class FavoriteViewModel(private val method: FavoriteMethod) : BaseViewModel() {

    private val _state = MutableLiveData<ListFavoriteState>()

    val state get() = _state

    fun list() = method.list()
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            _state.postValue(ListFavoriteState.OnLoading)
        }
        .subscribe({
            _state.postValue(ListFavoriteState.OnSuccess(it))
        }, {
            _state.postValue(ListFavoriteState.OnError(it.message ?: ""))
        })
        .disposeOnCleared()

    fun deleteAll() = method.truncate()
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            _state.postValue(ListFavoriteState.OnLoading)
        }
        .subscribe({
            method.list()
        }, {
            _state.postValue(ListFavoriteState.OnError(it.message ?: ""))
        })
        .disposeOnCleared()

}

sealed class ListFavoriteState {
    object OnLoading : ListFavoriteState()
    data class OnSuccess(val list: List<User>) : ListFavoriteState()
    data class OnError(val message: String) : ListFavoriteState()
}