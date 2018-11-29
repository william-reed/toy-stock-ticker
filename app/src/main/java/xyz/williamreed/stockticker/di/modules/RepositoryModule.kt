package xyz.williamreed.stockticker.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import xyz.williamreed.stockticker.data.repositories.AndroidStockRepository
import xyz.williamreed.stockticker.data.repositories.StockRepository
import xyz.williamreed.stockticker.data.services.StockService
import javax.inject.Singleton

@Module
class RepositoryModule {
    @[Provides Singleton]
    fun providesStockRepository(stockService: StockService, context: Context): StockRepository =
        AndroidStockRepository(stockService, context)
}
