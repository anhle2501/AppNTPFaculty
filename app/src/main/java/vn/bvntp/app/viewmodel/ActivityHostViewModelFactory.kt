package vn.bvntp.app.viewmodel


//class ActivityHostViewModelFactory(val hoSoBenhAnRepository: HoSoBenhAnRepository,
//        val userRepository: UserRepository
//) : Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ActivityHostViewModel(hoSoBenhAnRepository, userRepository ) as T
//        }
//
//        throw UnsupportedOperationException(
//            "Factory.create(String) is unsupported.  This Factory requires " +
//                    "`CreationExtras` to be passed into `create` method."
//        )
//    }
//
//
//}