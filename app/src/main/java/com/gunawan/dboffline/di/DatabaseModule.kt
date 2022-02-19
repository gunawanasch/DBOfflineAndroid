package com.gunawan.dboffline.di

import android.app.Application
import androidx.room.Room
import com.gunawan.dboffline.repository.local.room.DBOfflineDAO
import com.gunawan.dboffline.repository.local.room.DBOfflineDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): DBOfflineDatabase {
        return Room.databaseBuilder(application, DBOfflineDatabase::class.java, "dboffline")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDBOfflineDao(database: DBOfflineDatabase): DBOfflineDAO {
        return  database.dbOfflineDAO
    }

    single { provideDatabase(androidApplication()) }
    single { provideDBOfflineDao(get()) }
}