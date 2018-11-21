package xyz.williamreed.stockticker.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.williamreed.stockticker.data.services.StockService
import javax.inject.Singleton

@Module
class ServicesModule() {

    @[Provides Singleton]
    fun providesRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.iextrading.com/1.0")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @[Provides Singleton]
    fun providesStockService(retrofit: Retrofit) = retrofit.create(StockService::class.java)

}