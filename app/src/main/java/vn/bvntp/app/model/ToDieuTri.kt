package vn.bvntp.app.model

data class ToDieuTri(
    val MAICD:String,
    val CHANDOAN:String,
    val ID: String,
    val MABS: String,
    val NGAY: String,
    val DIENBIEN: String,
    val DIENBIENTUDO: String,
    val BSDIENBIEN: String,
    val IDKHOAPHONG: Int,
    val TENBENH: String,
    val MABENH: String,
    val SOTO: Int,
    val PHONG: String,
    val GIUONG: String,
    val TENKHOAPHONG: String,
    val BSYLENH: String,
    val YLENH: String,
    val YLENHTUDO: String,
    val IDYLENH: String,
    val CHONIN: Float,
    val SOVAOVIEN: String,
    val SUA: Int,
    val CHEDOCHAMSOC_ID: Int,
    val CHEDOCHAMSOC_TEN: String,
    val MASOKYTEN: String,
    val MAICDKEMTHEO: String,
    val CHANDOANKEMTHEO: String,
    val DAKY: Boolean,
    val TRANGTHAIKY: Int,
    val DAXEM: Float,
    val DATHUCHIEN: Float,
    val KHANCAP: Float,
    val LISTIDDAUSINHTON: String,
    val LISTIDDAUSINHTONEMR: String,
    val MABSDUYET: String,
    val BSDUYET: String,
    val MACHEDOAN: String,
    val TENCHEDOAN: String,
    val YLENHMOI: String,
    val DIENBIENMOI: String,
    val YKHAC: Boolean,
    val CHONINMOI: Boolean
)

data class ToDieuTriRequest(
    val MaQL: String
)

data class ToDieuTriResponse(
    val success: Boolean,
    val message: String,
    val data: ArrayList<ToDieuTri>,
)
