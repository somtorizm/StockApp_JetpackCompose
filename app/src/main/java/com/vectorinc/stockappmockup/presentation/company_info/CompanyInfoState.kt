package com.vectorinc.stockappmockup.presentation.company_info

import com.vectorinc.stockappmockup.domain.model.CompanyInfo
import com.vectorinc.stockappmockup.domain.model.IntraDayInfo

data class CompanyInfoState(
    val stockInfo : List<IntraDayInfo> = emptyList(),
    val company : CompanyInfo? = null,
    val isLoading : Boolean = false,
    val error : String? = null
)