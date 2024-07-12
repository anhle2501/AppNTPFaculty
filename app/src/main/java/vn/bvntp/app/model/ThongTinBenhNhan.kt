package vn.bvntp.app.model

data class ThongTinBenhNhanRequest(
    var MaBN: String,
    var SoVaoVien: String
)

data class ThongTinBenhNhanResponse(
    val Success: Boolean,
    val Message: String,
    val Data: ThongTinBenhNhan,
    val Code: Int
)

data class ThongTinBenhNhan(
    val HOTEN: String,
    val GIOITINH: String,
    val NAMSINH: Int,
    val DOITUONG: String,
    val MABN: String,
    val NGAYSINH: String,
    val TGVAOVIEN: String,
    val TGRAVIEN: String,
    val TGLUUTRU: String,
    val LOAIRAVIEN: String,
    val KHOAKETTHUC: String,
    val KHOADIEUTRI: String,
    val CHANDOAN: String,
    val LYDOTUCHOI: String,
    val THOIGIANTAO: String,
    val NGUOITAO: String,
    val THOIGIANSUA: String,
    val NGUOISUA: String,
    val ID: String,
    val TUCHOI: Int,
    val IDDUYETBHYT: String,
    val SOVAOVIEN: String
)