package com.vectorinc.stockappmockup.data.remote

import com.vectorinc.stockappmockup.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60mins&datatype=csv")
    suspend fun getIntraDayInfo(
        @Query("symbol") symbol : String,
        @Query("apikey") apikey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol : String,
        @Query("apikey") apikey: String = API_KEY
    ) : CompanyInfoDto

    companion object {
        const val API_KEY = "XJLR4TNIXBH1NOWD"
        const val BASE_URL = "https://alphavantage.co"
    }

}