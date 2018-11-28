package xyz.williamreed.stockticker.ui.watchlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import xyz.williamreed.stockticker.data.models.Quote
import xyz.williamreed.stockticker.data.repositories.StockRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WatchListViewModel @Inject constructor(private val stockRepository: StockRepository) : ViewModel() {

    private val quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>().also { initQuotes() }
    }
    // one of the problems with this is getting old data if you reopen the view since this behaves
    // similar to a BehaviorSubject.
    private val error: MutableLiveData<String> = MutableLiveData()
    private lateinit var stocksDisposable: Disposable
    private val tickers = listOf("AAPL", "GOOG", "FB")

    private fun initQuotes() {
        stocksDisposable = Observable.interval(0,5, TimeUnit.SECONDS)
            .flatMap { Observable.just(tickers) }
            .flatMapIterable { tickers }
            .map { stockRepository.quote(it).blockingGet() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(tickers.size)
            .subscribe({ quotes.value = it }, {
                // TODO: might be a problem having an android import in non android class
                Log.e("Stock Subscription", it.message)
                error.value = "Error getting quotes."
            })
    }

    override fun onCleared() {
        super.onCleared()
        stocksDisposable.dispose()
    }

    fun getQuotes(): LiveData<List<Quote>> = quotes
    fun getErrors(): LiveData<String> = error
}
