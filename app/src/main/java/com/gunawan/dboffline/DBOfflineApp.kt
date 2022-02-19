package com.gunawan.dboffline

import android.app.Application
import com.gunawan.dboffline.di.databaseModule
import com.gunawan.dboffline.di.networkModule
import com.gunawan.dboffline.di.repositoryModule
import com.gunawan.dboffline.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DBOfflineApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DBOfflineApp)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(listOf(databaseModule, networkModule, repositoryModule, viewModelModule))
        }
    }

}