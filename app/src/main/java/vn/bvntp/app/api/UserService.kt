package vn.bvntp.app.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import vn.bvntp.app.model.LoginResponse
import vn.bvntp.app.model.UserLoginInfo

interface UserService{
    @POST("login")
    fun login(@Body user: UserLoginInfo): Call<LoginResponse>
}
