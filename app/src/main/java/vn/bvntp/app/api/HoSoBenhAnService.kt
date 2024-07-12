package vn.bvntp.app.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import vn.bvntp.app.model.LichSuDieuTriResponse
import vn.bvntp.app.model.RequestLichSuDieuTri


interface HoSoBenhAnService {
    @POST("hsba/LoadLichSuDieuTri")
    fun getLichSuDieuTriResponse(@Header("Authorization") token: String, @Body reqLichSuDieuTri: RequestLichSuDieuTri) : Call<LichSuDieuTriResponse>
}