package vn.bvntp.app.model

import org.json.JSONArray

data class RequestVanBanKy(val MaVaoVien: String, val ListId: ArrayList<Int> )

data class VanBanKyResponse(
    val Success : Boolean,
    val Message: String,
    val Data: JSONArray,
    val Code: Int
)

data class RequestLichSuDieuTri(val MaBN: String)

data class LichSuDieuTriResponse(
    val Success : Boolean,
    val Message: String,
    val Data: ArrayList<ThongTinVaoVien>,
    val Code: Number
)

data class ThongTinVaoVien(
    val MAVAOVIEN: String,
    val MAQL: Number,
    val MACHUYENDEN: Number,
    val LOAIBA: Number,
    val IDKHOAPHONG: Number,
    val KHOAPHONG: String,
    val IDKHOAPHONGCHUYENDI: Number,
    val KHOAPHONGCHUYENDI: String,
    val MABENHVIENCHUYENDI: String,
    val BENHVIENCHUYENDI: String,
    val MAXUTRI: Number,
    val XUTRI: String,
    val IDTINHTRANGRAKHOA: Number,
    val IDTINHTRANGRAKHOA_XV: Number,
    val TINHTRANGRAVIEN: String,
    val BENHCHINH: String,
    val NGAYVAO: String,
    val NGAYRA: String,
    val TRANGTHAI: String,
    val DONE: Number
)