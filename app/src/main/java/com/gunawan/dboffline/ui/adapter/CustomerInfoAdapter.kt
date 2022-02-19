package com.gunawan.dboffline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunawan.dboffline.databinding.RowCustomerInfoBinding
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel

class CustomerInfoAdapter(private val listCustomerInfo: List<CustomerInfoModel>) : RecyclerView.Adapter<CustomerInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowCustomerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listCustomerInfo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.binding.tvName.text      = listCustomerInfo[position].name
        holder.binding.tvPhone.text     = listCustomerInfo[position].phone
    }

    class ViewHolder(val binding: RowCustomerInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

}