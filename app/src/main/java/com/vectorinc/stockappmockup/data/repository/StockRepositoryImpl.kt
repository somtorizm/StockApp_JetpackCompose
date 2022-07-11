package com.vectorinc.stockappmockup.data.repository

import com.vectorinc.stockappmockup.data.csv.CSVPaser
import com.vectorinc.stockappmockup.data.local.CompanyListingEntity
import com.vectorinc.stockappmockup.data.local.StockDatabase
import com.vectorinc.stockappmockup.data.mapper.toCompanyListingEntity
import com.vectorinc.stockappmockup.data.mapper.toCompanyListings
import com.vectorinc.stockappmockup.data.remote.StockApi
import com.vectorinc.stockappmockup.domain.model.CompanyListing
import com.vectorinc.stockappmockup.domain.repository.StockRepository
import com.vectorinc.stockappmockup.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
   private val api: StockApi,
   private val db: StockDatabase,
   private val companyListingPaser: CSVPaser<CompanyListing>
) : StockRepository {
    private val dao = db.dao
    override suspend fun getCompanyListings(
        fecthFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.isLoading(true))
            val localListings = dao.searchCompanyListings(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListings() }
            ))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fecthFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.isLoading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingPaser.paser(response.byteStream())


            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            }
            remoteListings?.let {listings->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map {
                        it.toCompanyListingEntity()
                    }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListings("").map { it.toCompanyListings() }
                ))
                emit(Resource.isLoading(false))

            }

        }
    }
}