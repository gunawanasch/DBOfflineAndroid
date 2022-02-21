package com.gunawan.dboffline.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunawan.dboffline.R
import com.gunawan.dboffline.databinding.ActivityCustomerInfoBinding
import com.gunawan.dboffline.databinding.ActivityMainBinding
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import com.gunawan.dboffline.ui.adapter.ContactAdapter
import com.gunawan.dboffline.ui.adapter.CustomerInfoAdapter
import com.gunawan.dboffline.viewmodel.ContactViewModel
import com.gunawan.dboffline.viewmodel.CustomerInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerInfoBinding
    private lateinit var customerInfoAdapter: CustomerInfoAdapter
    private val customerInfoViewModel by viewModel<CustomerInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbCustomerInfo.setNavigationIcon(R.drawable.ic_back)
        binding.tbCustomerInfo.title                = getString(R.string.customer_info)
        binding.pbCustomerInfo.visibility           = View.VISIBLE
        binding.srCustomerInfo.visibility           = View.GONE
        customerInfoViewModel.ldGetAllCustomerInfo  = MediatorLiveData()
        customerInfoViewModel.ldMsg                 = MutableLiveData()

        getAllCustomerInfo()

        binding.tbCustomerInfo.setNavigationOnClickListener {
            finish()
        }

        binding.srCustomerInfo.setOnRefreshListener {
            binding.srCustomerInfo.isRefreshing = true
            binding.pbCustomerInfo.visibility   = View.VISIBLE
            binding.srCustomerInfo.visibility   = View.GONE
            getAllCustomerInfo()
            getAllCustomerInfoMsg()
        }
    }

    private fun getAllCustomerInfo() {
        customerInfoViewModel.ldGetAllCustomerInfo  = MediatorLiveData()
        customerInfoViewModel.ldMsg                 = MutableLiveData()
        customerInfoViewModel.getAllCustomerInfo()
        customerInfoViewModel.ldGetAllCustomerInfo.observe(this, {
            binding.pbCustomerInfo.visibility = View.GONE
            binding.srCustomerInfo.visibility = View.VISIBLE
            if (binding.srCustomerInfo.isRefreshing) {
                binding.srCustomerInfo.isRefreshing = false
            }
            if (!it.isNullOrEmpty()) {
                customerInfoAdapter = CustomerInfoAdapter(it)
                binding.rvCustomerInfo.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@CustomerInfoActivity)
                    adapter = customerInfoAdapter
                }
            }
        })
    }

    @SuppressLint("ShowToast")
    private fun getAllCustomerInfoMsg() {
        customerInfoViewModel.ldMsg.observe(this, {
            if (binding.srCustomerInfo.isRefreshing) {
                binding.srCustomerInfo.isRefreshing = false
            }
            binding.pbCustomerInfo.visibility = View.GONE
            binding.srCustomerInfo.visibility = View.GONE
            Toast.makeText(this, it, Toast.LENGTH_SHORT)
        })
    }

}