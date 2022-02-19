package com.gunawan.dboffline.di

import com.gunawan.dboffline.repository.ContactRepository
import com.gunawan.dboffline.repository.CustomerInfoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ContactRepository(get())
    }

    single {
        CustomerInfoRepository(get(), get())
    }
}