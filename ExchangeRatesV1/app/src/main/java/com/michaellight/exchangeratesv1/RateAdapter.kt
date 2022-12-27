package com.michaellight.exchangeratesv1

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.michaellight.exchangeratesv1.databinding.ItemRateBinding
import java.security.AccessController.getContext


class RateAdapter : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {
	inner class RateViewHolder(val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root)

	private val diffCallback = object : DiffUtil.ItemCallback<Rate>() {
		override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
			return oldItem.Cur_ID == newItem.Cur_ID
		}

		override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
			return oldItem.Cur_ID == newItem.Cur_ID
		}
	}

	private val differ = AsyncListDiffer(this, diffCallback)
	var rates: List<Rate>
	get() = differ.currentList
	set(value) {
		differ.submitList(value)
	}

	override fun getItemCount(): Int {
		return rates.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
		return RateViewHolder(ItemRateBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		))
	}

	override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
		holder.binding.apply {
			val rate = rates[position]
			tvTitle.text = "BYN : " + rate.Cur_Abbreviation
			tvPrice.text = rate.Cur_OfficialRate.toString()
		}
	}
}