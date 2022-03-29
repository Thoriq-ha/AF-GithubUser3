package com.thor.githubuser3.UI.DetailUser.TabFollow

import androidx.lifecycle.MutableLiveData
import com.thor.githubuser3.Base.BaseViewModel
import com.thor.githubuser3.Repository.User.User
import io.reactivex.schedulers.Schedulers


class ProfileTabViewModel(private val useCase: ProfileTabUseCase) : BaseViewModel() {

    private val _state = MutableLiveData<ProfileTabState>()
    val state get() = _state

    fun list(type: Int, username: String) {
        when (type) {
            0 -> useCase.follower(username)
            else -> useCase.following(username)
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