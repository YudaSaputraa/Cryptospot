package com.kom.cryptospot.presentation.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import coil.load
import com.kom.cryptospot.R
import com.kom.cryptospot.databinding.FragmentProfileBinding
import com.kom.cryptospot.presentation.login.LoginActivity
import com.kom.cryptospot.presentation.profile.editprofile.EditProfileActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        showUSerData()
        doEditProfile()
        doLogout()
    }

    private fun showUSerData() {
        profileViewModel.getCurrentUser()?.let {
            binding.ivProfile.load(it.photoUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_person)
                error(R.drawable.ic_person)
            }
            binding.tvUsername.setText(it.fullName)
            binding.tvEmail.setText(it.email)
        }
    }

    private fun doEditProfile() {
        with(binding) {
            btnEditProfile.setOnClickListener {
                editProfileActivity(requireContext())
            }
        }
    }

    private fun editProfileActivity(context: Context) {
        startActivity(Intent(context, EditProfileActivity::class.java).apply { })
    }

    private fun doLogout() {
        binding.btnLogout.setOnClickListener {
            profileViewModel.isUserLoggedOut()
            navigateToLogin()

            requireActivity().supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE,
            )
        }
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(requireContext(), LoginActivity::class.java).apply {
            },
        )
    }
}
