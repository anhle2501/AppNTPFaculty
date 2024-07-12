package vn.bvntp.app.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.bvntp.app.helper.retrieveAccessDataDecryptedData
import vn.bvntp.app.model.ToDieuTri
import vn.bvntp.app.model.ToDieuTriRequest
import vn.bvntp.app.repository.ToDieuTriRepository

class ToDieuTriViewModel(val toDieuTriRepository: ToDieuTriRepository) : ViewModel() {

    // use for getting danh sach to dieu tri
    private val _maQl = MutableLiveData<ToDieuTriRequest>()
    val maQl: LiveData<ToDieuTriRequest> get() = _maQl
    // save danh sach to dieu tri
    private val _danhSachToDieuTri = MutableLiveData<ArrayList<ToDieuTri>>()
    val danhSachToDieuTri: LiveData<ArrayList<ToDieuTri>> get() = _danhSachToDieuTri
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress
    fun updateMaQl(toDieuTriRequest: ToDieuTriRequest){
        _maQl.value = toDieuTriRequest
    }

    fun updateIsLoading(boolean: Boolean) {
        _isLoading.value = boolean
    }

    fun getDsToDieuTri(): ArrayList<ToDieuTri> {
        return _danhSachToDieuTri.value!!
    }

    fun fectchData(context: Context, toDieuTriRequest: ToDieuTriRequest, next: (Boolean) -> Unit) {
        getDsToDieuTriViewModel(context, toDieuTriRequest, next)
    }

    fun getDsToDieuTriViewModel(context: Context, toDieuTriRequest: ToDieuTriRequest, next: (Boolean) -> Unit) {
        updateIsLoading(true)
        val accessToken = retrieveAccessDataDecryptedData(context, "access_token")
        viewModelScope.launch {
            if (accessToken != null) {
                toDieuTriRepository.getDanhSachToDieuTri(accessToken, toDieuTriRequest) { result ->
                    _isLoading.postValue(false)
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
    private fun handleSuccess(result: ArrayList<ToDieuTri>) {
        _danhSachToDieuTri.value = result
    }
    private fun handleFailure(throwable: Throwable) {
        _error.value = throwable.message
    }
}