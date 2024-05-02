package com.kom.cryptospot.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.kom.cryptospot.R
import com.kom.cryptospot.databinding.ActivityLoginBinding
import com.kom.cryptospot.presentation.main.MainActivity
import com.kom.cryptospot.presentation.register.RegisterActivity
import com.kom.foodapp.utils.highLightWord
import com.kom.foodapp.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupForm()
        setClickListener()
    }

    private fun setClickListener() {
        binding.layoutFormLogin.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.layoutFormLogin.tvNavToRegister.highLightWord(getString(R.string.highlight_sign_up)) {
            navigateToRegister()
        }
        binding.layoutFormLogin.tvForgetPassword.setOnClickListener {
            showResetPasswordDialog()
        }
    }

    private fun showResetPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.reset_password))

        val input = EditText(this)
        input.hint = getString(R.string.enter_email)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.send)) { dialog, _ ->
            val email = input.text.toString().trim()
            if (email.isNotEmpty()) {
                handleResetPassword(email)
            } else {
                Toast.makeText(this, getString(R.string.enter_email), Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun handleResetPassword(email: String) {
        loginViewModel.reqChangePasswordByEmailWithoutLogin(email).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    showResetPasswordSuccessDialog()
                },
                doOnError = {
                    Toast.makeText(
                        this,
                        getString(R.string.error, it.exception?.message),
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d(
                        "reqChangePasswordByEmailWithoutLogin",
                        getString(R.string.create_email_input_dialog, it.exception?.message),
                    )
                },
            )
        }
    }

    private fun showResetPasswordSuccessDialog() {
        val dialog =
            AlertDialog.Builder(this)
                .setMessage(getString(R.string.dialog_req_update_password))
                .setPositiveButton(
                    getString(R.string.ok),
                ) { dialog, id ->
                }.create()
        dialog.show()
    }

    private fun navigateToRegister() {
        startActivity(
            Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    private fun doLogin() {
        if (isFormValid()) {
            val email = binding.layoutFormLogin.etEmail.text.toString().trim()
            val password = binding.layoutFormLogin.etPassword.text.toString().trim()

            proceedLogin(email, password)
        }
    }

    private fun proceedLogin(
        email: String,
        password: String,
    ) {
        loginViewModel.doLogin(email, password).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    binding.layoutFormLogin.pbLoading.isVisible = false
                    binding.layoutFormLogin.btnLogin.isVisible = true
                    navigateToMain()
                },
                doOnError = {
                    binding.layoutFormLogin.pbLoading.isVisible = false
                    binding.layoutFormLogin.btnLogin.isVisible = true
                    Log.d("proceedLogin", getString(R.string.proceed_login, it.exception?.message))
                    Toast.makeText(
                        this,
                        getString(R.string.password_is_incorrect),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
                doOnLoading = {
                    binding.layoutFormLogin.pbLoading.isVisible = true
                    binding.layoutFormLogin.btnLogin.isVisible = false
                },
            )
        }
    }

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }

    private fun isFormValid(): Boolean {
        val email = binding.layoutFormLogin.etEmail.text.toString().trim()
        val password = binding.layoutFormLogin.etPassword.text.toString().trim()

        return checkEmailValidation(email) && checkPasswordValidation(password, binding.layoutFormLogin.tilPassword)
    }

    private fun checkPasswordValidation(
        confirmPassword: String,
        textInputLayout: TextInputLayout,
    ): Boolean {
        return if (confirmPassword.isEmpty()) {
            binding.layoutFormLogin.tilPassword.isErrorEnabled = true
            binding.layoutFormLogin.tilPassword.error = getString(R.string.error_password_empty)
            false
        } else {
            binding.layoutFormLogin.tilPassword.isErrorEnabled = false
            true
        }
    }

    private fun checkEmailValidation(email: String): Boolean {
        return if (email.isEmpty()) {
            binding.layoutFormLogin.tilEmail.isErrorEnabled = true
            binding.layoutFormLogin.tilEmail.error = getString(R.string.error_email_empty)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.layoutFormLogin.tilEmail.isErrorEnabled = true
            binding.layoutFormLogin.tilEmail.error = getString(R.string.error_email_invalid)
            false
        } else {
            binding.layoutFormLogin.tilEmail.isErrorEnabled = false
            true
        }
    }

    private fun setupForm() {
        with(binding.layoutFormLogin) {
            tilEmail.isVisible = true
            tilPassword.isVisible = true
        }
    }
}
