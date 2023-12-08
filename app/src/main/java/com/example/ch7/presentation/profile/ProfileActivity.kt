package com.example.ch7.presentation.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7.databinding.ActivityProfileBinding
import com.example.ch7.presentation.auth.login.LoginActivity
import com.example.ch7.presentation.blur.BlurActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private var binding: ActivityProfileBinding? = null
    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeLiveData()

        viewModel.loadProfile()
        viewModel.loadProfilePhoto()

        binding?.buttonLogout?.setOnClickListener {
            viewModel.logout()
        }

        binding?.ivProfile?.setOnClickListener {
            BlurActivity.startActivity(this)
        }
    }

    private fun observeLiveData() {
        viewModel.username.observe(this, ::handleUsername)
        viewModel.email.observe(this, ::handleEmail)
        viewModel.loading.observe(this, ::handleLoading)
        viewModel.error.observe(this, ::handleError)
        viewModel.logout.observe(this, ::handleLogout)
        viewModel.profilePhoto.observe(this, ::handleProfilePhoto)
        // viewModel.outputWorkerInfos.observe(this, ::handleWorkerInfos)
    }

    private fun handleUsername(username: String?) {
        binding?.etUsername?.setText(username)
    }

    private fun handleEmail(email: String?) {
        binding?.etEmail?.setText(email)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.flipperButton?.displayedChild = if (isLoading) 1 else 0
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLogout(isLogout: Boolean) {
        if (isLogout) {
            LoginActivity.startActivity(this)
        }
    }

    private fun handleProfilePhoto(profilePhoto: String?) {
        profilePhoto?.let {
            binding?.ivProfile?.setImageURI(Uri.parse(profilePhoto))
        }
    }
}
