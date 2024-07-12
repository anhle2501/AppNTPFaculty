package vn.bvntp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vn.bvntp.app.repository.ToDieuTriRepository

class ToDieuTriViewModelFactory(val toDieuTriRepository: ToDieuTriRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ToDieuTriViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDieuTriViewModel(toDieuTriRepository ) as T
        }

        throw UnsupportedOperationException(
            "Factory.create(String) is unsupported.  This Factory requires " +
                    "`CreationExtras` to be passed into `create` method."
        )
    }
}