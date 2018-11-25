package xyz.williamreed.stockticker.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import xyz.williamreed.stockticker.ui.watchlist.WatchListViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WatchListViewModel::class)
    abstract fun bindWatchListViewModel(viewModel: WatchListViewModel): ViewModel
}