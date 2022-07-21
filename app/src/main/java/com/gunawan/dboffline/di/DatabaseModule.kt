package com.gunawan.dboffline.di

import android.app.Application
import androidx.room.Room
import com.gunawan.dboffline.repository.local.room.DBOfflineDAO
import com.gunawan.dboffline.repository.local.room.DBOfflineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: DBOfflineDatabase.Callback): DBOfflineDatabase{
        return Room.databaseBuilder(application, DBOfflineDatabase::class.java, "dboffline")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideMovieAppDao(db: DBOfflineDatabase): DBOfflineDAO{
        return db.getDBOfflineDao()
    }

}