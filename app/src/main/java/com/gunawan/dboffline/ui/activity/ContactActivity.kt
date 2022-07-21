package com.gunawan.dboffline.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gunawan.dboffline.R
import com.gunawan.dboffline.databinding.ActivityContactBinding
import com.gunawan.dboffline.databinding.BottomSheetFormContactBinding
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import com.gunawan.dboffline.ui.adapter.ContactAdapter
import com.gunawan.dboffline.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var listContact: MutableList<ContactModel>
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbContact.setNavigationIcon(R.drawable.ic_back)
        binding.tbContact.title = getString(R.string.contact)

        getAllContact()

        binding.tbContact.setNavigationOnClickListener {
            finish()
        }

        binding.fabAdd.setOnClickListener {
            showBottomSheetFormContact(true, 0)
        }
    }

    private fun getAllContact() {
        contactViewModel.getAllContact()?.observe(this, Observer {
            listContact = it as MutableList<ContactModel>
            contactAdapter = ContactAdapter(it)
            binding.rvContact.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@ContactActivity)
                adapter = contactAdapter
                contactAdapter.setOnCustomClickListener(object : ContactAdapter.OnCustomClickListener {
                    override fun onEditClicked(position: Int) {
                        showBottomSheetFormContact(false, position)
                    }

                    override fun onDeleteClicked(position: Int) {
                        contactViewModel.deleteContact(
                            ContactModel(listContact.get(position).idContact,
                                listContact.get(position).name,
                                listContact.get(position).phone,
                                listContact.get(position).address
                            )
                        )
                        getAllContact()
                    }
                })
            }
        })
    }

    private fun showBottomSheetFormContact(isAdd: Boolean, position: Int) {
        val dialog = BottomSheetDialog(this)
        val bottomSheetFormContactBinding = BottomSheetFormContactBinding.inflate(layoutInflater, null, false)
        dialog.setCancelable(true)
        dialog.setContentView(bottomSheetFormContactBinding.root)
        dialog.show()
        bottomSheetFormContactBinding.tvError.visibility = View.INVISIBLE

        if (!isAdd) {
            bottomSheetFormContactBinding.etName.setText(listContact.get(position).name)
            bottomSheetFormContactBinding.etPhone.setText(listContact.get(position).phone)
            bottomSheetFormContactBinding.etAddress.setText(listContact.get(position).address)
        }

        bottomSheetFormContactBinding.btnSave.setOnClickListener {
            val name    = bottomSheetFormContactBinding.etName.text.toString()
            val phone   = bottomSheetFormContactBinding.etPhone.text.toString()
            val address = bottomSheetFormContactBinding.etAddress.text.toString()
            if (name.isBlank() || phone.isBlank() || address.isBlank()) {
                bottomSheetFormContactBinding.tvError.visibility    = View.VISIBLE
                bottomSheetFormContactBinding.tvError.text          = getString(R.string.form_not_complete)
            } else {
                if (isAdd) {
                    contactViewModel.insertContact(
                        ContactModel(name = name,
                            phone = phone,
                            address = address
                        )
                    )
                } else {
                    contactViewModel.updateContact(
                        ContactModel(listContact.get(position).idContact,
                            name,
                            phone,
                            address
                        )
                    )
                }
                bottomSheetFormContactBinding.etName.setText("")
                bottomSheetFormContactBinding.etPhone.setText("")
                bottomSheetFormContactBinding.etAddress.setText("")
                dialog.dismiss()
                getAllContact()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

}