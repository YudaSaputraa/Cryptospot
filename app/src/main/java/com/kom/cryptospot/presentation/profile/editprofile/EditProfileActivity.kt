package com.kom.cryptospot.presentation.profile.editprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.kom.cryptospot.R
import com.kom.cryptospot.databinding.ActivityEditProfileBinding
import com.kom.cryptospot.presentation.main.MainActivity
import com.kom.foodapp.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileActivity : AppCompatActivity() {
    private val binding: ActivityEditProfileBinding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    private val editProfileViewModel: EditProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.layoutForm.etEmail.isEnabled = false
        showUserData()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnReqChangePass.setOnClickListener {
            requestChangePassword()
        }

        binding.btnSaveEdit.setOnClickListener {
            doEditProfile()
        }
    }

    private fun showUserData() {
        editProfileViewModel.getCurrentUser()?.let {
            binding.ivProfile.load(it.photoUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_person)
                error(R.drawable.ic_person)
            }
            binding.layoutForm.etUsername.setText(it.fullName)
            binding.layoutForm.etEmail.setText(it.email)
        }
    }

    private fun doEditProfile() {
        if (checkNameValidation()) {
            val fullName = binding.layoutForm.etUsername.text.toString().trim()
            proceedEditProfile(fullName)
        }
    }

    private fun requestChangePassword() {
        editProfileViewModel.createRequestChangePass()
        val dialog =
            AlertDialog.Builder(this)
                .setMessage(
                    "Change password request sended to your email : ${editProfileViewModel.getCurrentUser()?.email} Please check to your inbox or spam",
                )
                .setPositiveButton(
                    "Okay",
                ) { dialog, id ->
                }.create()
        dialog.show()
    }

    private fun checkNameValidation(): Boolean {
        val fullName = binding.layoutForm.etUsername.text.toString().trim()
        return if (fullName.isEmpty()) {
            binding.layoutForm.tilUsername.isErrorEnabled = true
            binding.layoutForm.tilUsername.error =
                getString(R.string.text_error_name_cannot_empty)
            false
        } else {
            binding.layoutForm.tilUsername.isErrorEnabled = false
            true
        }
    }

    private fun proceedEditProfile(fullName: String) {
        editProfileViewModel.updateProfileName(fullName = fullName).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.visibility = View.INVISIBLE
                    binding.btnSaveEdit.visibility = View.VISIBLE
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_data_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                    navigateToMain()
                },
                doOnError = {
                    binding.pbLoading.visibility = View.INVISIBLE
                    binding.btnSaveEdit.visibility = View.VISIBLE
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_data_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnLoading = {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.btnSaveEdit.visibility = View.INVISIBLE
                },
            )
        }
    }

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        )
    }
}
