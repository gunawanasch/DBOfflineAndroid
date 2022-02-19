package com.gunawan.dboffline.repository.remote

import com.gunawan.dboffline.repository.remote.model.RespGetAllCustomerInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    @GET("getAllCustomerInfo")
    fun getAllCustomerInfo(): Observable<List<RespGetAllCustomerInfo>>
}