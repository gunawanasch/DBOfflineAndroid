package com.gunawan.dboffline.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gunawan.dboffline.repository.ContactRepository
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ContactViewModel(private val repo: ContactRepository) : ViewModel() {
    var ldGetAllContact: LiveData<List<ContactModel>>? = null

    fun insertContact(contact: ContactModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.insertContact(contact)
        }
    }

    fun updateContact(contact: ContactModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.updateContact(contact)
        }
    }

    fun deleteContact(contact: ContactModel) = runBlocking {
        this.launch(Dispatchers.IO) {
            repo.deleteContact(contact)
        }
    }

    fun getAllContact(): LiveData<List<ContactModel>>? {
        ldGetAllContact = repo.getAllContact()
        return ldGetAllContact
    }

}