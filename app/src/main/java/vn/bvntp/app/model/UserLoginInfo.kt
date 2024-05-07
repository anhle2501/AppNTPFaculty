package vn.bvntp.app.model

data class UserLoginInfo(val UserName: String, val Password: String)

data class LoginResponse(
    val success: Boolean,
    val errorMessage: String,
    val data: User
)




data class User (
    val IPServerGoiLoaTrungTam: Int ,
    val Cancel: Boolean ,
    val ListNhomThuoc: String ,
    val Password: String ,
    val CoThamGiaGoiSoThuTu: Boolean,
    val IDRoomVideo: Int,
    val IsToanQuyen: Boolean,
    val IsAdmin: Boolean,
    val MaNhanVien: Int,
    val TenBacSi: String,
    val HoTen: String,
    val ListIDBaoCaoNhanh: String,
    val ListNhomKho: String,
    val ListKhoaPhong: String,
    val ListKho: String,
    val ListDoiTuong: String ,
    val ListXuatKhac: String,
    val DuocThuHoiPhieuDuyetThuoc: Boolean,
    val DuocChuyenSoLieuDuTru: Boolean,
    val DuocTaoSoLieuThang: Boolean,
    val DuocSuaPhieuNhapKho: Boolean,
    val Pc: String,
    val ListIDKhoaPhongChiDinhPTTTAsString: String,
    val ListIDLoaiVienPhiPTTT: String,
    val NhomUser: Int,
    val UserName: String,
    val IDComputer:Int,
)

