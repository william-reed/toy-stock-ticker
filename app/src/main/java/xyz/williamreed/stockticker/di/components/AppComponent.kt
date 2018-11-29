package xyz.williamreed.stockticker.di.components

import android.app.Application
import dagger.Component
import xyz.williamreed.stockticker.data.services.StockService
import xyz.williamreed.stockticker.di.modules.*
import xyz.williamreed.stockticker.ui.watchlist.WatchListFragment
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class,
        ServicesModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)
interface AppComponent {
    fun inject(target: WatchListFragment)
}
