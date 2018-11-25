package xyz.williamreed.stockticker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.williamreed.stockticker.data.services.StockService
import xyz.williamreed.stockticker.ui.watchlist.WatchListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val stockService: StockService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WatchListViewModel::class.java)) {
            return WatchListViewModel(stockService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}