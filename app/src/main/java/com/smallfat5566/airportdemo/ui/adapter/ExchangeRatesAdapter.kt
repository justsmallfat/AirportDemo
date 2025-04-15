package com.smallfat5566.airportdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.smallfat5566.airportdemo.databinding.RecycleItemExchangeRatesBinding
import java.text.DecimalFormat


// Item 佈局的 View Binding
class ExchangeRatesAdapter(
    onItemClick: (Map.Entry<String, Double>) -> Unit
) : BaseListAdapter<Map.Entry<String, Double>, RecycleItemExchangeRatesBinding>(
    ItemDiffCallback, onItemClick
) {
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): RecycleItemExchangeRatesBinding {
        return RecycleItemExchangeRatesBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: RecycleItemExchangeRatesBinding): BaseViewHolder<Map.Entry<String, Double>, RecycleItemExchangeRatesBinding> {
        return ItemStockDayAvgViewHolder(binding)
    }

    class ItemStockDayAvgViewHolder(
        binding: RecycleItemExchangeRatesBinding
    ) : BaseViewHolder<Map.Entry<String, Double>, RecycleItemExchangeRatesBinding>(binding) {
        override fun bind(item: Map.Entry<String, Double>, onItemClick: (Map.Entry<String, Double>) -> Unit) {
            binding.ratesNameTextView.text = item.key
            val formatter = DecimalFormat("#,###.##")
            val formattedNumber = formatter.format(item.value)
            binding.ratesValueTextView.text = formattedNumber
        }
    }

    companion object {
        private val ItemDiffCallback = object : DiffUtil.ItemCallback<Map.Entry<String, Double>>() {
            override fun areItemsTheSame(oldItem: Map.Entry<String, Double>, newItem: Map.Entry<String, Double>): Boolean {
                return oldItem.key == newItem.key
            }

            override fun areContentsTheSame(oldItem: Map.Entry<String, Double>, newItem: Map.Entry<String, Double>): Boolean {
                return (oldItem.key == newItem.key && oldItem.value == newItem.value)
            }
        }
    }
}