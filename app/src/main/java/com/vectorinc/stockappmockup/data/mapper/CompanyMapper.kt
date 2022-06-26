package com.vectorinc.stockappmockup.data.mapper

import com.vectorinc.stockappmockup.data.local.CompanyListingEntity
import com.vectorinc.stockappmockup.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListings(): CompanyListing{
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}
fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}