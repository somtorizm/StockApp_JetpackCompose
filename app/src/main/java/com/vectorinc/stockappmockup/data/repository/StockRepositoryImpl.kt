package com.vectorinc.stockappmockup.data.repository

import com.vectorinc.stockappmockup.data.local.StockDatabase
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
    val api: StockApi,
    val db: StockDatabase
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

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))

            }


        }
    }
}