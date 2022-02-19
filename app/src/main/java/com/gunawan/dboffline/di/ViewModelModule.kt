package com.gunawan.dboffline.di

import com.gunawan.dboffline.viewmodel.ContactViewModel
import com.gunawan.dboffline.viewmodel.CustomerInfoViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        ContactViewModel(get())
    }

    single {
        CustomerInfoViewModel(get())
    }
}