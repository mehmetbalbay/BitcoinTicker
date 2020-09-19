package com.mehmetbalbay.bitcointicker.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mehmetbalbay.bitcointicker.api.ApiResponse
import com.mehmetbalbay.bitcointicker.models.Envelope
import com.mehmetbalbay.bitcointicker.models.Resource
import timber.log.Timber

@Suppress("LeakingThis")
abstract class NetworkBoundRepository<ResultType, RequestType>
internal constructor() {

    private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

    init {
        Timber.d("Injection NetworkBoundRepository")
        val loadedFromDb = loadFromDb()

        result.addSource(loadedFromDb) { data ->
            result.removeSource(loadedFromDb)
            if (shouldFetch(data)) {
                result.postValue(Resource.loading(null, null))
                fetchFromNetwork(loadFromDb())
            } else {
                result.addSource<ResultType>(loadedFromDb) { newData ->
                    setValue(
                        Resource.success(
                            newData,
                            1
                        )
                    )
                }
            }
        }
    }

    private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>) {
        val apiResponse = fetchService()

        result.addSource(apiResponse) { response ->
            response?.let {
                when (response.isSuccessful) {
                    true -> {
                        response.body?.let {
                            saveFetchData(it)
                            val loaded = loadFromDb()

                            result.addSource(loaded) { newData ->
                                newData?.let {
                                    setValue(Resource.success(newData, response.nextPage))
                                }
                            }
                        }
                    }
                    false -> {
                        result.removeSource(loadedFromDB)
                        onFetchFailed(response.envelope)
                        response.envelope?.let {
                            result.addSource<ResultType>(loadedFromDB) { newData ->
                                setValue(
                                    Resource.error(it.message, newData)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        result.value = newValue
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    @WorkerThread
    protected abstract fun saveFetchData(items: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun fetchService(): LiveData<ApiResponse<RequestType>>

    @MainThread
    protected abstract fun onFetchFailed(envelope: Envelope?)
}