package com.gunawan.dboffline.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gunawan.dboffline.repository.local.room.DBOfflineDatabase
import com.gunawan.dboffline.repository.local.room.model.ContactModel

class ContactRepository(private val db: DBOfflineDatabase) {

    suspend fun insertContact(contact: ContactModel) = db.dbOfflineDAO.insertContact(contact)

    suspend fun updateContact(contact: ContactModel) = db.dbOfflineDAO.updateContact(contact)

    suspend fun deleteContact(contact: ContactModel) = db.dbOfflineDAO.deleteContact(contact)

    fun getAllContact(): LiveData<List<ContactModel>> = db.dbOfflineDAO.getAllContact()

}