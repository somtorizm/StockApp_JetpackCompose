package com.vectorinc.stockappmockup.di

import com.vectorinc.stockappmockup.data.csv.CSVPaser
import com.vectorinc.stockappmockup.data.csv.CompanyListingPaser
import com.vectorinc.stockappmockup.data.csv.IntradayInfoPaser
import com.vectorinc.stockappmockup.data.repository.StockRepositoryImpl
import com.vectorinc.stockappmockup.domain.model.CompanyListing
import com.vectorinc.stockappmockup.domain.model.IntraDayInfo
import com.vectorinc.stockappmockup.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun companyListingPaser(
        companyListingPaser: CompanyListingPaser
    ): CSVPaser<CompanyListing>

    @Binds
    @Singleton
    abstract fun infoDayInfoParser(
        infoDayInfoPaser: IntradayInfoPaser
    ): CSVPaser<IntraDayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ) : StockRepository
}