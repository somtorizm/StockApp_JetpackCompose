package com.vectorinc.stockappmockup.presentation.company_listing

import com.vectorinc.stockappmockup.domain.model.CompanyListing

data class CompanyListingState(
    val companies : List<CompanyListing> = emptyList(),
    val isLoading : Boolean = false,
    val isRefreshing : Boolean = false,
    val searchQuery: String = ""
)