package com.gunawan.dboffline.repository

import androidx.lifecycle.LiveData
import com.gunawan.dboffline.repository.local.room.DBOfflineDatabase
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel
import com.gunawan.dboffline.repository.remote.APIService
import com.gunawan.dboffline.repository.remote.model.RespGetAllCustomerInfo
import io.reactivex.Observable

class CustomerInfoRepository(private val db: DBOfflineDatabase, private val api: APIService) {

    fun getAllCustomerInfoAPI(): Observable<List<RespGetAllCustomerInfo>> {
        return api.getAllCustomerInfo()
    }

    suspend fun insertCustomerInfo(customerInfo: CustomerInfoModel) = db.dbOfflineDAO.insertCustomerInfo(customerInfo)

    suspend fun clearCustomerInfo() = db.dbOfflineDAO.clearCustomerInfo()

    fun getAllCustomerInfoDB(): LiveData<List<CustomerInfoModel>> = db.dbOfflineDAO.getAllCustomerInfo()

}