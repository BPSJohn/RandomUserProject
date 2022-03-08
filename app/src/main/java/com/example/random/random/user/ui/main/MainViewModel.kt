package com.example.random.random.user.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.random.random.user.data.model.RandomUserResult
import com.example.random.random.user.data.remote.networkmodel.ServiceResult
import com.example.random.random.user.data.remote.repo.RandomUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val dispatcher : Dispatchers,
    private val repo : RandomUserRepo
) : AndroidViewModel(application) {

    private val _randomUserFeed = MutableLiveData<RandomUserResult?>()

    val randomUserFeed : LiveData<RandomUserResult?>
        get() = _randomUserFeed

    private val _randomUsersFeed = MutableLiveData<RandomUserResult?>()

    val randomUsersFeed : LiveData<RandomUserResult?>
        get() = _randomUsersFeed

    fun getUser() {
        viewModelScope.launch(dispatcher.IO) {
            when(val response = repo.fetchUser())
            {
                is ServiceResult.Success -> {
                    _randomUserFeed.postValue(response.data)
                    Timber.d("Got a random user. " + response.data)
                }

                is ServiceResult.Error -> {
                    Timber.d("Error was found calling random user :: " + response.exception)
                }

                else ->
                {
                    Timber.d("Uh oh for getUser().")
                }
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch(dispatcher.IO) {
            when(val response = repo.fetchUsers())
            {
                is ServiceResult.Success -> {
                    _randomUsersFeed.postValue(response.data)
                    Timber.d("Got random users. " + response.data)
                }

                is ServiceResult.Error -> {
                    Timber.d("Error was found calling random users :: " + response.exception)
                }

                else ->
                {
                    Timber.d("Uh oh for getUsers().")
                }
            }
        }
    }

    fun updateUser(user: RandomUserResult?) {
        _randomUserFeed.value = user
    }

    fun saveUser() : RandomUserResult? {
        _randomUserFeed.value?.results?.get(0)?.login?.uuid = ""
        _randomUserFeed.value?.results?.get(0)?.login?.password = ""
        _randomUserFeed.value?.results?.get(0)?.login?.salt = ""
        _randomUserFeed.value?.results?.get(0)?.login?.md5 = ""
        _randomUserFeed.value?.results?.get(0)?.login?.sha1 = ""
        _randomUserFeed.value?.results?.get(0)?.login?.sha256 = ""

        return _randomUserFeed.value
    }
}
