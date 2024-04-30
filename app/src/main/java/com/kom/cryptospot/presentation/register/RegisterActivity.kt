package com.kom.cryptospot.presentation.register


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kom.cryptospot.databinding.ActivityLoginBinding

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
