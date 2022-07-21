package com.gunawan.dboffline.repository.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gunawan.dboffline.di.ApplicationScope
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = arrayOf(ContactModel::class, CustomerInfoModel::class), version = 1, exportSchema = false)
abstract class DBOfflineDatabase : RoomDatabase() {
    abstract fun getDBOfflineDao(): DBOfflineDAO

    class Callback @Inject constructor(
        private val database: Provider<DBOfflineDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()

}