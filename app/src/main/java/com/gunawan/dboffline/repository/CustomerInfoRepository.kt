package com.gunawan.dboffline.repository

import androidx.lifecycle.LiveData
import com.gunawan.dboffline.repository.local.room.DBOfflineDAO
import com.gunawan.dboffline.repository.local.room.DBOfflineDatabase
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel
import com.gunawan.dboffline.repository.remote.APIService
import com.gunawan.dboffline.repository.remote.model.RespGetAllCustomerInfo
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerInfoRepository @Inject constructor(private val dbOfflineDAO: DBOfflineDAO, private val api: APIService) {

    fun getAllCustomerInfoAPI(): Observable<List<RespGetAllCustomerInfo>> {
        return api.getAllCustomerInfo()
    }

    suspend fun insertCustomerInfo(customerInfo: CustomerInfoModel) = dbOfflineDAO.insertCustomerInfo(customerInfo)

    suspend fun clearCustomerInfo() = dbOfflineDAO.clearCustomerInfo()

    fun getAllCustomerInfoDB(): LiveData<List<CustomerInfoModel>> = dbOfflineDAO.getAllCustomerInfo()

}