package vn.bvntp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import vn.bvntp.app.repository.ThongTinBenhNhanRepository


class ThongTinBenhNhanViewModelFactory(val thongTinBenhNhanRep: ThongTinBenhNhanRepository) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ThongTinBenhNhanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThongTinBenhNhanViewModel(thongTinBenhNhanRep ) as T
        }

        throw UnsupportedOperationException(
            "Factory.create(String) is unsupported.  This Factory requires " +
                    "`CreationExtras` to be passed into `create` method."
        )
    }


}