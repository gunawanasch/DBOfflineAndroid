package com.gunawan.dboffline.repository.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel

@Database(entities = arrayOf(ContactModel::class, CustomerInfoModel::class), version = 1, exportSchema = false)
abstract class DBOfflineDatabase : RoomDatabase() {
    abstract val dbOfflineDAO: DBOfflineDAO
}