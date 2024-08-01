package vn.bvntp.app.repository

import vn.bvntp.app.network.RetrofitClient
import vn.bvntp.app.viewmodel.DsHienDienViewModelFactory
import vn.bvntp.app.viewmodel.HoSoBenhAnViewModelFactory
import vn.bvntp.app.viewmodel.LoginViewModelFactory
import vn.bvntp.app.viewmodel.ThongTinBenhNhanViewModelFactory
import vn.bvntp.app.viewmodel.ToDieuTriViewModelFactory

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

    // thông tin bệnh nhân
    val thongTinBenhNhanService = RetrofitClient.provideThongTinBenhNhanService()
    val thongTinBenhNhanRepo = ThongTinBenhNhanRepository(thongTinBenhNhanService)
    val thongTinBenhNhanFactory = ThongTinBenhNhanViewModelFactory(thongTinBenhNhanRepo)

    // Tờ điều trị
    val toDieuTriService = RetrofitClient.provideToDieuTriService()
    val toDieuTriRepo = ToDieuTriRepository(toDieuTriService)
    val toDieuTriFactory = ToDieuTriViewModelFactory(toDieuTriRepo)

    // Ds hien dien
    val dsHienDienService = RetrofitClient.provideDsHienDienService()
    val dsHienDienRepo = DanhSachHienDienRepository(dsHienDienService)
    val dsHienDienFactory = DsHienDienViewModelFactory(dsHienDienRepo)

    // Validator
//    val validator = Validator();
}