package com.gunawan.dboffline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.gunawan.dboffline.R
import com.gunawan.dboffline.databinding.RowContactBinding
import com.gunawan.dboffline.repository.local.room.model.ContactModel

class ContactAdapter(private val listContact: List<ContactModel>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    private var listener: OnCustomClickListener? = null

    interface OnCustomClickListener {
        fun onEditClicked(position: Int)
        fun onDeleteClicked(position: Int)
    }

    fun setOnCustomClickListener(listener: OnCustomClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
        holder.binding.tvName.text      = listContact[position].name
        holder.binding.tvPhone.text     = listContact[position].phone
        holder.binding.tvAddress.text   = listContact[position].address
        holder.binding.ivOptionMenu.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, holder.binding.ivOptionMenu)
            popupMenu.menuInflater.inflate(R.menu.option_menu_list,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_edit ->
                        listener?.onEditClicked(position)
                    R.id.action_delete ->
                        listener?.onDeleteClicked(position)
                }
                true
            }
            popupMenu.show()
        }
    }

    class ViewHolder(val binding: RowContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

}