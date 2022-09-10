package com.utsman.reqres.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diana.subscriber.convertEventToSubscriber
import com.utsman.reqres.data.User
import com.utsman.reqres.repository.UserRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userManager = userRepository.userStateEventManager

    private val userScope = userManager.createScope(viewModelScope)

    fun subscribeUser(subscriber: com.diana.subscriber.StateEventSubscriber<List<User>>) {
        convertEventToSubscriber(userManager, subscriber)
    }

    fun getUsers(page: Int) = userScope.launch {
        userRepository.getUsers(page)
    }

    companion object {
        fun inject() = module {
            viewModel { MainViewModel(get()) }
        }
    }
}