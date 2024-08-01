package vn.bvntp.app.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bvntp.app.api.DanhSachHienDienService
import vn.bvntp.app.model.DanhSachHienDienResponse
import vn.bvntp.app.model.ThongTinBenhNhan
import vn.bvntp.app.model.ThongTinBenhNhanRequest
import vn.bvntp.app.model.ThongTinBenhNhanResponse
import vn.bvntp.app.model.ThongTinHienDien

class DanhSachHienDienRepository (val apiService: DanhSachHienDienService) {

    fun getDsHienDien(token: String, idKhoa: Int, onResult: (Result<ArrayList<ThongTinHienDien>>) -> Unit) {

        apiService.getDsHienDienResponse(token, idKhoa).enqueue( object :
            Callback<DanhSachHienDienResponse> {
            override fun onResponse(call: Call<DanhSachHienDienResponse>, response: Response<DanhSachHienDienResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    // get token
                    if (body != null) {
                        if (body.success == true) {
                            var dsHienDien = body.data
                            onResult(Result.success(dsHienDien))
                        }
                    }
                } else {
                    onResult(Result.failure(throw UnknownError(response.errorBody().toString())))
                }
            }
            override fun onFailure(call: Call<DanhSachHienDienResponse>, t: Throwable) {
                onResult(Result.failure(throw Exception(t.toString())))
            }
        })
    }
}