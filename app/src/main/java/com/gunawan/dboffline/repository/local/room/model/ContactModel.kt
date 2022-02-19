package com.gunawan.dboffline.repository.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_contact")
    var idContact: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "address")
    var address: String
)