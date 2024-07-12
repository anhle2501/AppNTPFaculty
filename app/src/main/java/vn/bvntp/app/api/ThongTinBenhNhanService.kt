package vn.bvntp.app.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import vn.bvntp.app.model.ThongTinBenhNhanRequest
import vn.bvntp.app.model.ThongTinBenhNhanResponse

interface ThongTinBenhNhanService {
    @POST("hsba/LoadThongTinHanhChinh")
    fun getThongTinBenhNhan(@Header("Authorization") token: String, @Body thongTinBenhNhanRequest: ThongTinBenhNhanRequest): Call<ThongTinBenhNhanResponse>
}
