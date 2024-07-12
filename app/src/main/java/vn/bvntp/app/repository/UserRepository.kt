package vn.bvntp.app.repository


import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bvntp.app.api.UserService
import vn.bvntp.app.helper.storeEncryptedData
import vn.bvntp.app.model.LoginResponse
import vn.bvntp.app.model.UserLoginInfo


class UserRepository (val apiService: UserService) {

    fun userRepositoryProvide():UserRepository {
        return UserRepository(apiService)
    }
    fun login(context: Context, username: String, password: String, onResult: (Result<LoginResponse>) -> Unit) {
        val loginData = UserLoginInfo(username, password)

        apiService.login(loginData).enqueue( object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // get token
                    if (response.headers().get("Authorization") != null) {
                        val accessToken = response.headers().get("Authorization")
                        val body = response.body()

                        if (accessToken != null) {
                            storeEncryptedData(context, "access_token", accessToken)
                            if (body != null) {
                                storeEncryptedData(context, "ten_bac_si", body.data.TenBacSi)
                                storeEncryptedData(context, "user_name", body.data.UserName)
                            }
                        }
                    }

                    // body info
                    val loginResponse = response.body()
                    loginResponse?.let {
                        onResult(Result.success(it))
                    }
                } else {
                    onResult(Result.failure(throw UnknownError("Login failed")))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(Result.failure(throw Exception("Không có phản hồi từ server")))
            }

            })
    }
}
