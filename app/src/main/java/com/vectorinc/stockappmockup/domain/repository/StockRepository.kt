package com.vectorinc.stockappmockup.domain.repository

import com.vectorinc.stockappmockup.domain.model.CompanyListing
import com.vectorinc.stockappmockup.util.Resource

interface StockRepository {

    suspend fun getCompanyListings(
        fecthFromRemote: Boolean,
        query: String
    ): kotlinx.coroutines.flow.Flow<Resource<List<CompanyListing>>>
}