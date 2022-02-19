package com.gunawan.dboffline.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunawan.dboffline.repository.CustomerInfoRepository
import com.gunawan.dboffline.repository.local.room.model.ContactModel
import com.gunawan.dboffline.repository.local.room.model.CustomerInfoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomerInfoViewModel(private val repo: CustomerInfoRepository) : ViewModel() {
    private var disposables         = CompositeDisposable()
    var ldGetAllCustomerInfo        = MediatorLiveData<List<CustomerInfoModel>>()
    var ldMsg                       = MutableLiveData<String>()

    fun getAllCustomerInfo() {
        disposables.add(
            repo.getAllCustomerInfoAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {}
                .doOnComplete {}
                .doOnError {
                    ldMsg.value = it.message
                }
                .subscribe {
                    if (it.isNotEmpty()) {
                        GlobalScope.launch(Dispatchers.Main) {
                            for (i in it.indices) {
                                repo.insertCustomerInfo(
                                    CustomerInfoModel(it.get(i).idCustomerInfo!!,
                                        it.get(i).name!!,
                                        it.get(i).phone!!
                                    )
                                )
                            }
                        }
                    }
                    ldGetAllCustomerInfo.addSource(repo.getAllCustomerInfoDB()) {
                        ldGetAllCustomerInfo.value = it
                    }
                }
        )
    }

}