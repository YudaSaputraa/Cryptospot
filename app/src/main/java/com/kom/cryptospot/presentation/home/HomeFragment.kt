package com.kom.cryptospot.presentation.home

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kom.cryptospot.R
import com.kom.cryptospot.data.model.coin.Coin
import com.kom.cryptospot.data.source.network.services.CryptospotApiService
import com.kom.cryptospot.databinding.FragmentHomeBinding
import com.kom.cryptospot.presentation.home.adapter.CoinAdapter
import com.kom.foodapp.utils.proceedWhen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private var coinItems: List<Coin>? = null

    private val coinAdapter: CoinAdapter by lazy {
        CoinAdapter {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            getDataFromApi(it.coinId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        bindCoinList()
        loadCoinsData()
        setDisplayName()
        setupRadioGroup()
    }

    private fun setDisplayName() {
        if (!homeViewModel.isUserLoggedIn()) {
            binding.layoutBanner.tvGreetings.apply {
                text = getString(R.string.text_user_not_login)
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            val currentUser = homeViewModel.getCurrentUser()
            binding.layoutBanner.tvGreetings.text =
                getString(R.string.text_greetings_name, currentUser?.fullName)
        }
    }

    private fun loadCoinsData(vsCurrency: String = "usd") {
        coinItems?.let { bindCoins(it, vsCurrency) } ?: getCoinData(vsCurrency)
    }

    private fun bindCoinList() {
        binding.rvCoins.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.coinAdapter
        }
    }

    private fun getCoinData(vsCurrency: String) {
        homeViewModel.getCoins(vsCurrency).observe(viewLifecycleOwner) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    with(binding) {
                        layoutState.root.isVisible = false
                        layoutOnEmptyDataState.root.isVisible = false
                        layoutState.pbLoading.isVisible = false
                        layoutOnEmptyDataState.ivOnEmptyData.isVisible = false
                        layoutOnEmptyDataState.tvOnEmptyData.isVisible = false
                        rvCoins.isVisible = true
                        result.payload?.let {
                            coinItems = it
                            bindCoins(it, vsCurrency)
                        }
                    }
                },
                doOnError = {
                    with(binding) {
                        layoutState.root.isVisible = true
                        layoutOnEmptyDataState.root.isVisible = true
                        layoutState.pbLoading.isVisible = false
                        layoutOnEmptyDataState.ivOnEmptyData.isVisible = true
                        layoutOnEmptyDataState.tvOnEmptyData.text =
                            getString(R.string.text_on_error)
                        rvCoins.isVisible = false
                        Log.d("GetCoins", "getCoinData: ${it.exception?.message}")
                    }
                },
                doOnEmpty = {
                    with(binding) {
                        layoutState.root.isVisible = true
                        layoutOnEmptyDataState.root.isVisible = true
                        layoutState.pbLoading.isVisible = false
                        layoutOnEmptyDataState.ivOnEmptyData.isVisible = true
                        layoutOnEmptyDataState.tvOnEmptyData.isVisible = true
                        rvCoins.isVisible = false
                    }
                },
                doOnLoading = {
                    with(binding) {
                        layoutState.root.isVisible = true
                        layoutOnEmptyDataState.root.isVisible = true
                        layoutState.pbLoading.isVisible = true
                        layoutOnEmptyDataState.ivOnEmptyData.isVisible = false
                        layoutOnEmptyDataState.tvOnEmptyData.isVisible = false
                        rvCoins.isVisible = false
                    }
                },
            )
        }
    }

    private fun getDataFromApi(id: String) {
        val apiService = CryptospotApiService.invoke()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getCoinById(id)
                Log.d("Coins", "Response: $response")
            } catch (e: Exception) {
                Log.e("Coins Error", "Error: ${e.message}", e)
            }
        }
    }

    private fun bindCoins(
        coins: List<Coin>,
        vsCurrency: String,
    ) {
        this.coinItems = coins
        coinAdapter.submitData(coins, vsCurrency)
    }

    private fun setupRadioGroup() {
        binding.layoutRadioVsCurrency.rgVsCurrency.setOnCheckedChangeListener { _, _ ->
            val selectedCurrency = getSelectedCurrency()
            coinAdapter.setVsCurrency(selectedCurrency)
            Log.d("SelectedCurrency", "Selected Currency: $selectedCurrency")
            getCoinData(selectedCurrency)
        }
    }

    private fun getSelectedCurrency(): String {
        val checkedRadioButtonId = binding.layoutRadioVsCurrency.rgVsCurrency.checkedRadioButtonId
        return when (checkedRadioButtonId) {
            R.id.rb_idr -> "idr"
            R.id.rb_usd -> "usd"
            else -> "usd"
        }
    }
}
