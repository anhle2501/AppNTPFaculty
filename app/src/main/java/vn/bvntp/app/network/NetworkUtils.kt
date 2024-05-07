package vn.bvntp.app.network


import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.bvntp.app.api.HoSoBenhAnService
import vn.bvntp.app.api.UserService


object RetrofitClient {


    private val retrofit by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =
            OkHttpClient.Builder().addInterceptor(logging)
            .build()

        Retrofit.Builder()
        .baseUrl("http://115.75.5.67:8089/api/")
        .addConverterFactory(GsonConverterFactory.create())
             .client(client)
        .build()

    }


    val userService by lazy {
        retrofit.create(UserService::class.java)

    }

    val HoSoBenhAnService by lazy {

        retrofit.create(HoSoBenhAnService::class.java)
    }



    fun provideUserService(): UserService {
        var service = userService
        return service
    }

    fun provideHoSoBenhAnService(): HoSoBenhAnService {
        var service = HoSoBenhAnService
        return service
    }
}


fun addHeaders(request: Request): Request {
    val builder = request.newBuilder()
    builder.addHeader("Authorization", "Bearer <access_token>")
    builder.addHeader("Content-Type", "application/json")
    return builder.build()
}

