package com.thor.githubuser3.UI.ViewModel

import androidx.lifecycle.MutableLiveData
import com.thor.githubuser3.Base.BaseViewModel
import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.UI.Page.DetailUser.TabFollow.FollowTabMethod
import io.reactivex.schedulers.Schedulers


class ProfileTabViewModel(private val method: FollowTabMethod) : BaseViewModel() {

    private val _state = MutableLiveData<ProfileTabState>()
    val state get() = _state

    fun list(type: Int, username: String) {
        when (type) {
            0 -> method.follower(username)
            else -> method.following(username)
        }.subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _state.postValue(ProfileTabState.OnLoading)
            }.subscribe({
                _state.postValue(ProfileTabState.OnSuccess(it))
            }, {
                _state.postValue(ProfileTabState.OnError(it?.message ?: "Terjadi Kesalahan"))
            }).disposeOnCleared()
    }
}

sealed class ProfileTabState {
    object OnLoading : ProfileTabState()
    data class OnSuccess(val list: List<User>) : ProfileTabState()
    data class OnError(val message: String) : ProfileTabState()
}