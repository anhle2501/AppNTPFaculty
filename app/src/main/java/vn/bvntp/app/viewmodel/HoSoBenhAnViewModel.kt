package vn.bvntp.app.viewmodel

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.bvntp.app.repository.HoSoBenhAnRepository
import java.io.File
import java.util.Collections
import java.util.stream.Collectors
import kotlin.math.log


class HoSoBenhAnViewModel(
    val repository: HoSoBenhAnRepository,
) : ViewModel() {

    private val _isLock = MutableLiveData(false)
    val isLock: LiveData<Boolean> get() = _isLock

    private val _isLockList = MutableLiveData(false)
    val isLockList: LiveData<Boolean> get() = _isLockList

    private val _maBenhNhan = MutableLiveData("")
    val maBenhNhan: LiveData<String> = _maBenhNhan

    private var _lichSuDieuTri = MutableLiveData(ArrayList<String>())
    val lichSuDieuTri: LiveData<ArrayList<String>> = _lichSuDieuTri

    private val _maVaoVien = MutableLiveData("")
    val maVaoVien: LiveData<String> = _maVaoVien

    private val _listId = MutableLiveData(ArrayList<Int>())
    val listId: LiveData<ArrayList<Int>> = _listId

    private lateinit var temp: String
    fun getTemp(): String {
        return temp
    }
    fun setMaVaoVienVaListId(maVaoVien: String, listId: ArrayList<Int>) {
        _maVaoVien.value = maVaoVien
        _listId.value = listId
    }
    fun toggleIsLockList() {
        _isLockList.value = !_isLockList.value!!
    }

    fun toggleIsLock() {
        _isLock.value = !_isLock.value!!
    }

    fun updateMaBenhNhan(maBenhNhan: String) {
        _maBenhNhan.value = maBenhNhan
    }

    fun modelHoSoBenhAnView(context: Context, callBack: () -> Unit) {
        viewModelScope.launch {
            _maVaoVien.value?.let {
                _listId.value?.let { it1 ->
                    repository.getHoSoBenhAn(
                        context = context,
                        maVaoVien = it,
                        listId = it1
                    ) {
                        if (it.isSuccess) {
                            it.onSuccess {

                                temp = "file://" + it.absolutePath

                                Log.d("temp", temp)
                                launch { callBack() }

                            }
                        } else {
                            it.onFailure {
                                launch {
                                    toggleIsLockList()
                                    toggleIsLock()
                                }
                                Handler(Looper.getMainLooper()).post {
                                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    fun modelLichSuVaoVienView(context: Context) {
        viewModelScope.launch {

            repository.getLichSuVaoVien(
                context = context,
                maBenhNhan = _maBenhNhan.value!!
            ) { result ->
                result.onSuccess { lichSuDieuTriResponse ->
                    if (lichSuDieuTriResponse.Success) {

                        // gom lich su dieu tri lai
                        var filter =
                            lichSuDieuTriResponse.Data.stream().map({ e -> e.MAVAOVIEN }).distinct()
                                .collect(
                                    Collectors.toList()
                                )

                        _lichSuDieuTri.postValue(filter as ArrayList<String>)

                        toggleIsLock()

                    } else {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context, lichSuDieuTriResponse.Message, Toast.LENGTH_SHORT).show()
                        }
                        toggleIsLock()

                    }
                }
                result.onFailure { vanBanKyResponse ->
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(context, vanBanKyResponse.message, Toast.LENGTH_SHORT).show()
                    }

                    Log.d("vanBanKyResponse", "failed")
                }
            }
        }


    }
}



