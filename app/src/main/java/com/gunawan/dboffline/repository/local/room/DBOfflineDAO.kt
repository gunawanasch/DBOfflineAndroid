package com.gunawan.dboffline.repository.local.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel

@Dao
interface DBOfflineDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactModel)

    @Update
    suspend fun updateContact(contact: ContactModel)

    @Delete
    suspend fun deleteContact(contact: ContactModel)

    @Query("SELECT * FROM contact")
    fun getAllContact() : LiveData<List<ContactModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerInfo(customerInfo: CustomerInfoModel)

    @Query("DELETE FROM customer_info")
    suspend fun clearCustomerInfo()

    @Query("SELECT * FROM customer_info")
    fun getAllCustomerInfo() : LiveData<List<CustomerInfoModel>>

}