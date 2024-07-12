package vn.bvntp.app.repository


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bvntp.app.api.ThongTinBenhNhanService
import vn.bvntp.app.model.ThongTinBenhNhan
import vn.bvntp.app.model.ThongTinBenhNhanRequest
import vn.bvntp.app.model.ThongTinBenhNhanResponse


class ThongTinBenhNhanRepository (val apiService: ThongTinBenhNhanService) {

    fun getThongTinBenhNhan(token: String, thongTinBenhNhanRequest: ThongTinBenhNhanRequest, onResult: (Result<ThongTinBenhNhan>) -> Unit) {

        apiService.getThongTinBenhNhan(token, thongTinBenhNhanRequest).enqueue( object : Callback<ThongTinBenhNhanResponse> {
            override fun onResponse(call: Call<ThongTinBenhNhanResponse>, response: Response<ThongTinBenhNhanResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    // get token
                    if (body != null) {
                        if (body.Success == true) {
                            var thongTinBenhNhan = body.Data
                            onResult(Result.success(thongTinBenhNhan))
                        }
                    }
                } else {
                    onResult(Result.failure(throw UnknownError(response.errorBody().toString())))
                }
            }
            override fun onFailure(call: Call<ThongTinBenhNhanResponse>, t: Throwable) {
                onResult(Result.failure(throw Exception(t.toString())))
            }
        })
    }
}
