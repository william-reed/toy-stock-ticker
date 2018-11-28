package xyz.williamreed.stockticker.data.repositories

import android.content.Context
import io.reactivex.Single
import xyz.williamreed.stockticker.data.models.Price
import xyz.williamreed.stockticker.data.models.Quote
import xyz.williamreed.stockticker.data.services.StockService
import javax.inject.Inject

// TODO: kosher to have interface and impl in same class? are android imports ok during testing?
interface StockRepository {
    fun price(symbol: String): Single<Price>
    fun quote(symbol: String): Single<Quote>
    val symbols: Set<String>
}

class AndroidStockRepository @Inject constructor(
    private val stockService: StockService,
    private val context: Context
) : StockRepository {
    override fun price(symbol: String) = stockService.price(symbol)
    override fun quote(symbol: String) = stockService.quote(symbol)

    override var symbols: Set<String> = emptySet()
        set(value) {
            val content = value.fold("") { acc, current -> acc + current.trim() + "," }.removeSuffix(",")
            context.openFileOutput(filename, Context.MODE_PRIVATE).use {
                it.write(content.toByteArray())
            }
        }

    companion object {
        const val filename = "tickers.xyz"
    }
}
