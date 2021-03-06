package xyz.williamreed.stockticker.data.models

data class Price(val price: Int)

data class Quote(
    val symbol: String,
    val companyName: String,
    val primaryExchange: String,
    val sector: String,
    val open: Double,
    val close: Double,
    val high: Double,
    val low: Double,
    val latestPrice: Double,
    val change: Double,
    val changePercent: Double
)