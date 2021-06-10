package com.dicoding.expert.core.data

import com.dicoding.expert.core.data.source.remote.network.APIRespon
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is APIRespon.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is APIRespon.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is APIRespon.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        }else{
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<APIRespon<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow():Flow<Resource<ResultType>> = result
}