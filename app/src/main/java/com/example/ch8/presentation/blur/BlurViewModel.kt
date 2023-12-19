package com.example.ch8.presentation.blur

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import com.example.ch8.domain.repository.AccountRepository
import com.example.ch8.domain.repository.ImageRepository
import com.example.ch8.helper.toUriOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlurViewModel(
    private val imageRepository: ImageRepository,
    private val accountRepository: AccountRepository,
) : ViewModel() {
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _profilePhoto = MutableLiveData<String?>()
    val profilePhoto: LiveData<String?> = _profilePhoto

    private var imageUri: Uri? = null
    private var outputUri: Uri? = null

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = outputImageUri.toUriOrNull()
    }

    internal fun setImageUri(imageUri: String?) {
        this.imageUri = imageUri.toUriOrNull()
    }

    fun saveProfilePhoto(profilePhoto: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.saveProfilePhoto(profilePhoto)
        }
    }

    fun applyBlur() {
        imageRepository.applyBlur(imageUri)
    }

    fun getOutputWorkerInfo(): LiveData<List<WorkInfo>> {
        return imageRepository.getWorkManagerLiveData()
    }

    fun loadProfilePhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            accountRepository.loadProfilePhoto()
                .catch { throwable ->
                    withContext(Dispatchers.Main) {
                        _error.value = throwable.message
                    }
                }
                .collectLatest { profilePhoto ->
                    withContext(Dispatchers.Main) {
                        _profilePhoto.value = profilePhoto
                    }
                }
        }
    }
}
