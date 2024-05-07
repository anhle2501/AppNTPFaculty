package vn.bvntp.app.api

import android.util.JsonToken
import com.google.gson.JsonObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import vn.bvntp.app.model.LichSuDieuTriResponse
import vn.bvntp.app.model.RequestLichSuDieuTri
import vn.bvntp.app.model.RequestVanBanKy
import vn.bvntp.app.model.UserLoginInfo
import vn.bvntp.app.model.VanBanKyResponse

interface HoSoBenhAnService {


    @POST("hsba/LoadLichSuDieuTri")
    fun getLichSuDieuTriResponse(@Header("Authorization") token: String, @Body reqLichSuDieuTri: RequestLichSuDieuTri) : Call<LichSuDieuTriResponse>

//    @Headers(
//        "Content-Type: application/json",
//        "User-Agent: PostmanRuntime/7.37.3"
//    )
//    @POST("hsba/LoadInVanBanKy")
//    fun getVanBanKyResponse(@Header("Authorization") token: String, @Body reqVanBan: JsonObject): Call<ResponseBody>
}