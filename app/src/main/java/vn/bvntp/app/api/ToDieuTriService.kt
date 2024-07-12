package vn.bvntp.app.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import vn.bvntp.app.model.ToDieuTriRequest
import vn.bvntp.app.model.ToDieuTriResponse

interface ToDieuTriService {
    @POST("hdnt/DSToDieuTri")
    fun getDanhSachToDieuTri(@Header("Authorization") token: String, @Body toDieuTriRequest: ToDieuTriRequest): Call<ToDieuTriResponse>
}