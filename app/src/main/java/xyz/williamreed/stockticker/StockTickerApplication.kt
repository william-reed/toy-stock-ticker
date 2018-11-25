package xyz.williamreed.stockticker

import android.app.Application
import xyz.williamreed.stockticker.di.components.AppComponent
import xyz.williamreed.stockticker.di.DaggerAppComponent
import xyz.williamreed.stockticker.di.modules.ServicesModule

class StockTickerApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .servicesModule(ServicesModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}