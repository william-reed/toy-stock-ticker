package xyz.williamreed.stockticker.di

import android.app.Application
import dagger.Component
import xyz.williamreed.stockticker.data.services.StockService
import xyz.williamreed.stockticker.ui.watchlist.WatchListFragment
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
//        AndroidSupportInjectionModule::class,
        AppModule::class,
        ServicesModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class]
)
interface AppComponent {
    fun stockService(): StockService
    fun inject(app: Application)
    fun inject(fragment: WatchListFragment)
}