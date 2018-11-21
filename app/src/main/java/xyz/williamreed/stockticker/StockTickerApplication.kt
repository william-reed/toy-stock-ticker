package xyz.williamreed.stockticker

import android.app.Application
import xyz.williamreed.stockticker.di.AppComponent
import xyz.williamreed.stockticker.di.AppModule
import xyz.williamreed.stockticker.di.DaggerAppComponent
import xyz.williamreed.stockticker.di.ServicesModule

class StockTickerApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .servicesModule(ServicesModule())
            .build()
        component.inject(this)
    }
}