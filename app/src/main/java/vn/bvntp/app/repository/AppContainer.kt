package vn.bvntp.app.repository

import androidx.lifecycle.SavedStateHandle
import vn.bvntp.app.helper.Validator
import vn.bvntp.app.network.RetrofitClient
import vn.bvntp.app.repository.UserRepository
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModel
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModelFactory
import vn.bvntp.app.viewmodel.LoginViewModelFactory

// Container of objects shared across the whole app
class AppContainer {

    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
     val userService = RetrofitClient.provideUserService()
     val userRepository  = UserRepository(userService)
     val viewModelFactory = LoginViewModelFactory(userRepository)

    // ho so benh an
    val hoSoBenhAnService = RetrofitClient.provideHoSoBenhAnService()
    val hsbaRep  = HoSoBenhAnRepository(hoSoBenhAnService)
    val hsbaViewModelFactory = HoSoBenhAnViewModelFactory(hsbaRep)

    // Validator
//    val validator = Validator();
}