package vn.bvntp.app.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.bvntp.app.helper.retrieveAccessDataDecryptedData
import vn.bvntp.app.model.ThongTinHienDien

import vn.bvntp.app.repository.DanhSachHienDienRepository


class DsHienDienViewModel(
    val repository: DanhSachHienDienRepository
) : ViewModel() {

    private val _dsHienDien = MutableLiveData< ArrayList<ThongTinHienDien>>();

    val dsHienDien: LiveData< ArrayList<ThongTinHienDien>> get() = _dsHienDien!!
    fun getDsHienDienViewModel(context: Context, idKhoa: Int, next: (Boolean) -> Unit) {

        val accessToken = retrieveAccessDataDecryptedData(context, "access_token")
        viewModelScope.launch {
            if (accessToken != null) {
                repository.getDsHienDien(accessToken, idKhoa) { result ->

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
    private fun handleSuccess(result: ArrayList<ThongTinHienDien>) {

    }
    private fun handleFailure(throwable: Throwable) {

    }

}