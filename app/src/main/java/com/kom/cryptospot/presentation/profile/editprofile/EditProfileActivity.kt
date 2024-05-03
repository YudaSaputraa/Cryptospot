package com.kom.cryptospot.presentation.profile.editprofile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.kom.cryptospot.R
import com.kom.cryptospot.databinding.ActivityEditProfileBinding
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
            doEditProfileEmail()
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

    private fun doEditProfileEmail() {
        val email = binding.layoutForm.etEmail.text.toString().trim()
        proceedEditProfileEmail(email)
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
                    binding.pbLoading.isVisible = false
                    binding.btnSaveEdit.isVisible = true
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_data_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.btnSaveEdit.isVisible = true
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_data_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.btnSaveEdit.isVisible = false
                },
            )
        }
    }

    private fun proceedEditProfileEmail(email: String) {
        editProfileViewModel.updateProfileEmail(email = email).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.btnSaveEdit.isVisible = true
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_data_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.btnSaveEdit.isVisible = true
                    Toast.makeText(
                        this,
                        getString(R.string.text_change_profile_data_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.btnSaveEdit.isVisible = false
                },
            )
        }
    }
}
