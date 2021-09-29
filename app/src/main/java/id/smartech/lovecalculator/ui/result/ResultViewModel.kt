package id.smartech.lovecalculator.ui.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.smartech.lovecalculator.remote.ApiService
import id.smartech.lovecalculator.ui.result.model.ResultModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ResultViewModel: ViewModel(), CoroutineScope {
    private lateinit var services: ApiService
    private var host = "love-calculator.p.rapidapi.com"
    private var key = "a6e992bc7emshe26a9249c3686f0p11201bjsnc458c25768fa"

    val onSuccessLiveData = MutableLiveData<ResultModel>()
    val onFailLiveData = MutableLiveData<String>()
    val isLoadingLiveData = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setService(services: ApiService) {
        this.services = services
    }

    fun getPercentageResult(fname: String, sname: String) {
        launch {
            isLoadingLiveData.value = true
            val response = withContext(Dispatchers.IO) {
                try {
                    services.getPercentage(host, key, fname = fname, sname = sname)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main){
                        isLoadingLiveData.value = false
                    }
                }
            }

            if (response is ResultModel) {
                isLoadingLiveData.value = false

                onSuccessLiveData.value = response
            } else {
                isLoadingLiveData.value = false
                onFailLiveData.value = "Get Data Failed"
            }
        }
    }


}