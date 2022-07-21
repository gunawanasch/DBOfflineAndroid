package com.gunawan.dboffline.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gunawan.dboffline.repository.local.room.DBOfflineDAO
import com.gunawan.dboffline.repository.local.room.DBOfflineDatabase
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(private val dbOfflineDAO: DBOfflineDAO) {

    suspend fun insertContact(contact: ContactModel) = dbOfflineDAO.insertContact(contact)

    suspend fun updateContact(contact: ContactModel) = dbOfflineDAO.updateContact(contact)

    suspend fun deleteContact(contact: ContactModel) = dbOfflineDAO.deleteContact(contact)

    fun getAllContact(): LiveData<List<ContactModel>> = dbOfflineDAO.getAllContact()

}