package vn.bvntp.app.network


//import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.bvntp.app.api.DanhSachHienDienService
import vn.bvntp.app.api.HoSoBenhAnService
import vn.bvntp.app.api.ThongTinBenhNhanService
import vn.bvntp.app.api.ToDieuTriService
import vn.bvntp.app.api.UserService

object RetrofitClient {
    private val retrofit by lazy {

//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =
            OkHttpClient.Builder()
//                .addInterceptor(logging)
            .build()

        Retrofit.Builder()
        .baseUrl("http://bvntp.com:8089/api/")
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
    val ThongTinBenhNhanService by lazy {
        retrofit.create(ThongTinBenhNhanService::class.java)
    }

    val ToDieuTriService by lazy {
        retrofit.create(ToDieuTriService::class.java)
    }

    val DsHienDienService by lazy {
        retrofit.create(DanhSachHienDienService::class.java)
    }

    fun provideUserService(): UserService {
        var service = userService
        return service
    }

    fun provideHoSoBenhAnService(): HoSoBenhAnService {
        var service = HoSoBenhAnService
        return service
    }

    fun provideThongTinBenhNhanService(): ThongTinBenhNhanService {
        var service = ThongTinBenhNhanService
        return service
    }

    fun provideToDieuTriService(): ToDieuTriService {
        var service = ToDieuTriService
        return service
    }

    fun provideDsHienDienService(): DanhSachHienDienService {
        var service = DsHienDienService
        return service
    }
}


fun addHeaders(request: Request): Request {
    val builder = request.newBuilder()
    builder.addHeader("Authorization", "Bearer <access_token>")
    builder.addHeader("Content-Type", "application/json")
    return builder.build()
}

