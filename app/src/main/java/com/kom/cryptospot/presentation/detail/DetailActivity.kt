package com.kom.cryptospot.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.kom.cryptospot.data.model.coin.CoinDetail
import com.kom.cryptospot.databinding.ActivityDetailBinding
import com.kom.cryptospot.utils.formatToUSD
import com.kom.foodapp.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val coinViewModel: DetailViewModel by viewModel {
        parametersOf(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        observeData(coinViewModel.coinId)
    }

    private fun observeData(id: String?) {
        coinViewModel.coinData(id).observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.svCoinDetail.isVisible = true
                    it.payload?.let { data -> bindCoin(data) }
                },
                doOnLoading = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.layoutState.tvError.isVisible = false
                    binding.svCoinDetail.isVisible = false
                },
                doOnError = {
                    binding.layoutState.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = true
                    binding.layoutState.tvError.text = it.exception?.message.orEmpty()
                    binding.svCoinDetail.isVisible = false
                },
            )
        }
    }

    private fun setClickListener() {
        binding.icDetailBackButton.setOnClickListener {
            finish()
        }
        binding.btnGoToCoinWeb.setOnClickListener {
            goToWeb()
        }
    }

    private fun goToWeb() {
        val url = coinViewModel.link
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun bindCoin(coin: CoinDetail?) {
        coin?.let { item ->
            binding.layoutHeaderCoinDetail.tvDetailTitle.text = item.name
            binding.layoutHeaderCoinDetail.tvDetailPrice.text = item.price.formatToUSD()
            binding.layoutHeaderCoinDetail.imgDetailCryptoLogo.load(item.imgURL) {
                crossfade(true)
            }
            binding.layoutDescCoinDetail.tvDetailDesc.text = item.coinDesc
        }
    }

//    private fun checkedIdr(price: String): Boolean {
//        val lastTwoChars = price.takeLast(3)
//        Log.d("last two", lastTwoChars)
//        return lastTwoChars.contains(",")
//    }

    companion object {
        const val EXTRA_COIN = "EXTRA_COIN"
        const val EXTRA_PRICE = "EXTRA_PRICE"

        fun startActivity(
            context: Context,
            coinId: String?,
        ) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_COIN, coinId)
            context.startActivity(intent)
        }
    }
}
