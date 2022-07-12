package com.vectorinc.stockappmockup.presentation.company_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vectorinc.stockappmockup.domain.repository.StockRepository
import com.vectorinc.stockappmockup.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {
    var state by mutableStateOf(CompanyListingState())
    private var searchJob : Job? = null

    init {
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingsEvent) {
        when (event) {
            is CompanyListingsEvent.Refresh -> {
                  getCompanyListings(fetchFromRemote = true)
            }
            is CompanyListingsEvent.onSearchQueryChanged -> {
                   state = state.copy(searchQuery =  event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    private fun getCompanyListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {

        viewModelScope.launch {
            repository.getCompanyListings(fetchFromRemote,query)
                .collect{resultx->
                    when(resultx){
                        is Resource.Success->{
                             resultx.data?.let {listings ->

                                 state  = state.copy(
                                     companies = listings
                                 )
                             }
                        }
                        is Resource.Error ->{

                        }
                        is Resource.isLoading->{
                        }
                    }
                }
        }
    }

}