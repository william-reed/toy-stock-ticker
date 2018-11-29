package xyz.williamreed.stockticker

import android.app.Application
import xyz.williamreed.stockticker.di.components.AppComponent
import xyz.williamreed.stockticker.di.components.DaggerAppComponent
import xyz.williamreed.stockticker.di.modules.AppModule

class StockTickerApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
    }

    private fun initDagger(app: Application) =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}
