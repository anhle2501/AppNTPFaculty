package vn.bvntp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vn.bvntp.app.repository.DanhSachHienDienRepository

class DsHienDienViewModelFactory (val danhSachHienDienRepository: DanhSachHienDienRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HoSoBenhAnViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DsHienDienViewModel(danhSachHienDienRepository ) as T
        }

        throw UnsupportedOperationException(
            "Factory.create(String) is unsupported.  This Factory requires " +
                    "`CreationExtras` to be passed into `create` method."
        )
    }
}