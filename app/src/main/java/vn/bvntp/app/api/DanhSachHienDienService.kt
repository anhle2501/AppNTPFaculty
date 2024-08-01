package vn.bvntp.app.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import vn.bvntp.app.model.DanhSachHienDienResponse

interface DanhSachHienDienService {
    @GET("hsba/hdnt/DanhSachHienDien?IDKhoaPhong={idKhoa}")
    fun getDsHienDienResponse(@Header("Authorization") token: String, @Path("idKhoa") idKhoa: Int) : Call<DanhSachHienDienResponse>

}