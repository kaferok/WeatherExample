package com.invatechs.weatherexample

import android.app.Application
import com.invatechs.data.di.DataModule
import com.invatechs.domain.di.DomainModule
import com.invatechs.weatherexample.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(DataModule.module + DomainModule.module + PresentationModule.module)
        }
    }
}