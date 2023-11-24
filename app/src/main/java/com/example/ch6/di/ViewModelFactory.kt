package com.example.ch6.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Deprecated(message = "replace by library", level = DeprecationLevel.ERROR)
class ViewModelFactory(
    //private val provider: Provider,
) : ViewModelProvider.Factory {

//    companion object {
//
//        @Volatile
//        private var INSTANCE : ViewModelFactory? = null
//
//        fun getInstance(provider: Provider) = synchronized(ViewModelFactory::class.java) {
//            INSTANCE ?: ViewModelFactory(provider).also { INSTANCE = it }
//        }
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
//            LoginViewModel::class.java -> LoginViewModel(
//                loginRepository = provider.localRepository,
//            )
//            HomeViewModel::class.java -> HomeViewModel(
//                homeRepository = provider.remoteRepository,
//            )
//            RegisterViewModel::class.java -> RegisterViewModel(
//                registerRepository = provider.localRepository,
//            )
//            ProfileViewModel::class.java -> ProfileViewModel(
//                profileRepository = provider.localRepository,
//                workManager = provider.workManager,
//            )
//            BlurViewModel::class.java -> BlurViewModel(
//                application = provider.context,
//                workManager = provider.workManager,
//            )
            else -> throw UnsupportedOperationException()
        } as T
    }
}