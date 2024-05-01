package com.kom.cryptospot.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kom.cryptospot.R
import com.kom.cryptospot.data.model.Coin
import com.kom.cryptospot.databinding.ItemCoinsBinding
import com.kom.cryptospot.utils.formatToUSD

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class CoinAdapter(
    private val itemClick: (Coin) -> Unit,
) : RecyclerView.Adapter<CoinAdapter.ItemCoinViewHolder>() {
    private val asyncDataDiffer =
        AsyncListDiffer<Coin>(
            this,
            object : DiffUtil.ItemCallback<Coin>() {
                override fun areItemsTheSame(
                    oldItem: Coin,
                    newItem: Coin,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Coin,
                    newItem: Coin,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(data: List<Coin>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemCoinViewHolder {
        val binding = ItemCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCoinViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(
        holder: ItemCoinViewHolder,
        position: Int,
    ) {
        holder.bindView(asyncDataDiffer.currentList[position])
    }

    class ItemCoinViewHolder(
        private val binding: ItemCoinsBinding,
        val itemClick: (Coin) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Coin) {
            with(item) {
                binding.ivCoinImage.load(item.imgURL) {
                    crossfade(true)
                    error(R.mipmap.ic_launcher)
                }
                binding.tvCoinName.text = item.name
                binding.tvCoinSymbols.text = item.coinSymbols
                binding.tvCoinPrice.text = item.price.formatToUSD()
            }
        }
    }
}
