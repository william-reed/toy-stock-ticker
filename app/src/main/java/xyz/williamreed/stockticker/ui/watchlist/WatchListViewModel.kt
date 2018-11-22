package xyz.williamreed.stockticker.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import xyz.williamreed.stockticker.data.models.Quote
import xyz.williamreed.stockticker.data.services.StockService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WatchListViewModel : ViewModel() {
    private val quotes: MutableLiveData<List<Quote>> by lazy {

        MutableLiveData<List<Quote>>().also {
            initQuotes()
        }
    }
    private val error: MutableLiveData<String> = MutableLiveData()

    @Inject
    lateinit var stockService: StockService
    lateinit var stocksDisposable: Disposable

    val tickers = listOf("AAPL", "GOOG", "FB")

    private fun initQuotes() {
        stocksDisposable = Observable.interval(5, TimeUnit.SECONDS)
            .flatMap { Observable.just(tickers) }
            .flatMapIterable { tickers }
            .map { stockService.quote(it).blockingGet() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(tickers.size)
            .subscribe({ quotes.value = it }, { error.value = "Error getting quotes." })
    }

    override fun onCleared() {
        super.onCleared()
        stocksDisposable.dispose()
    }

    fun getQuotes(): LiveData<List<Quote>> = quotes
    fun getErrors(): LiveData<String> = error
}