package vn.bvntp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import vn.bvntp.app.repository.HoSoBenhAnRepository


class HoSoBenhAnViewModelFactory(val hsbaRepository: HoSoBenhAnRepository) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HoSoBenhAnViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HoSoBenhAnViewModel(hsbaRepository ) as T
        }

        throw UnsupportedOperationException(
            "Factory.create(String) is unsupported.  This Factory requires " +
                    "`CreationExtras` to be passed into `create` method."
        )
    }


}