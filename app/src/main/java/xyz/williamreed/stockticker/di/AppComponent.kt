package xyz.williamreed.stockticker.di

import android.app.Application
import dagger.Component
import xyz.williamreed.stockticker.data.services.StockService
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ServicesModule::class]
)
interface AppComponent {
    fun stockService(): StockService
    fun inject(app: Application)
}