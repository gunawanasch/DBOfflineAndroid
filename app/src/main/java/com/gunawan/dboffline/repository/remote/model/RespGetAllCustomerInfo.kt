package com.gunawan.dboffline.repository.remote.model

import com.google.gson.annotations.SerializedName

data class RespGetAllCustomerInfo(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("id_customer_info")
	val idCustomerInfo: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)
