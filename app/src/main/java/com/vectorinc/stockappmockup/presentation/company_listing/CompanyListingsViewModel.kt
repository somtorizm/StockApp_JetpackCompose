package com.vectorinc.stockappmockup.presentation.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vectorinc.stockappmockup.domain.repository.StockRepository
import com.vectorinc.stockappmockup.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {
    val state by mutableStateOf(CompanyListingState())

    fun onEvent(event: CompanyListingsEvent) {
        when (event) {
            is CompanyListingsEvent.Refresh -> {

            }
            is CompanyListingsEvent.onSearchQueryChanged -> {

            }
        }
    }

    fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {

        viewModelScope.launch {
            repository.getCompanyListings(fetchFromRemote,query)
                .collect{result->
                    when(result){
                        is Resource.Success->{

                        }
                        is Resource.Error ->{

                        }
                    }
                }
        }
    }

}