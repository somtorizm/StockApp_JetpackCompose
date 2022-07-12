package com.vectorinc.stockappmockup.domain.repository

import com.vectorinc.stockappmockup.domain.model.CompanyInfo
import com.vectorinc.stockappmockup.domain.model.CompanyListing
import com.vectorinc.stockappmockup.domain.model.IntraDayInfo
import com.vectorinc.stockappmockup.util.Resource

interface StockRepository {

    suspend fun getCompanyListings(
        fecthFromRemote: Boolean,
        query: String
    ): kotlinx.coroutines.flow.Flow<Resource<List<CompanyListing>>>

    suspend fun getIntraDayInfo(
        symbol : String
    ): Resource<List<IntraDayInfo>>

    suspend fun getCompanyInfo(
        symbol : String
    ): Resource<CompanyInfo>
}