package com.vectorinc.stockappmockup.presentation.company_listing

sealed class CompanyListingsEvent {
    object Refresh : CompanyListingsEvent()
    data class onSearchQueryChanged(val query: String) : CompanyListingsEvent()
}