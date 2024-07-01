package vn.bvntp.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityHostViewModel: ViewModel() {

    private var _maBenhNhan = MutableLiveData("")
    val maBenhNhan: LiveData<String> = _maBenhNhan

}