package com.gunawan.dboffline.repository.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_info")
data class CustomerInfoModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_customer_info")
    var idCustomerInfo: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "phone")
    var phone: String,
)