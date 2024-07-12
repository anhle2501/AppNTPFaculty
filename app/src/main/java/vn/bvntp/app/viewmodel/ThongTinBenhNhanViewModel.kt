package vn.bvntp.app.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.bvntp.app.helper.retrieveAccessDataDecryptedData
import vn.bvntp.app.model.ThongTinBenhNhan
import vn.bvntp.app.model.ThongTinBenhNhanRequest
import vn.bvntp.app.repository.ThongTinBenhNhanRepository


class ThongTinBenhNhanViewModel(val thongTinBenhNhanRep:ThongTinBenhNhanRepository): ViewModel() {

//    private var _thongTinBenhNhanRequest = MutableLiveData<ThongTinBenhNhanRequest>()
//    val thongTinBenhNhanRequest get() = _thongTinBenhNhanRequest

    var _thongTinBenhNhan = MutableLiveData<ThongTinBenhNhan>()
    val thongTinBenhNhan get() = _thongTinBenhNhan

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    init {
//        _thongTinBenhNhanRequest.value?.MaBN = ""
//        _thongTinBenhNhanRequest.value?.SoVaoVien = ""
    }

//    fun updateThongTinBenhNhanRequest(MaBN: String, SoVaoVien: String) {
//        _thongTinBenhNhanRequest.value?.MaBN = MaBN
//        _thongTinBenhNhanRequest.value?.SoVaoVien = SoVaoVien
//
//        Log.d("updateThongTinBenhNhanRequest", _thongTinBenhNhanRequest.value?.MaBN.toString())
//    }

    fun getThongTinBenhNhanViewModel(
        context: Context,
        thongTinBenhNhanRequest: ThongTinBenhNhanRequest,
        next: (Boolean) -> Unit)
    {
        val accessToken = retrieveAccessDataDecryptedData(context, "access_token")
        viewModelScope.launch {
            if (accessToken != null) {
                thongTinBenhNhanRep.getThongTinBenhNhan(accessToken, thongTinBenhNhanRequest) { result ->

                    result.onSuccess { response ->
                        response.let {
                            handleSuccess(it)
                            next(true)
                        }
                    }.onFailure { throwable ->
                        handleFailure(throwable)
                        next(false)
                    }
                }
            }
        }
    }
    private fun handleSuccess(result: ThongTinBenhNhan) {
        _thongTinBenhNhan.value = result
    }
    private fun handleFailure(throwable: Throwable) {
        _error.value = throwable.message
    }


}
