package xyz.williamreed.stockticker.data.services

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.williamreed.stockticker.data.models.Price
import xyz.williamreed.stockticker.data.models.Quote

/**
 * Service for getting stock price / quote.
 */
interface StockService {

    @GET("stock/{symbol}/price")
    fun price(@Path("symbol") symbol: String): Single<Price>

    @GET("stock/{symbol}/quote")
    fun quote(@Path("symbol") symbol: String): Single<Quote>
}