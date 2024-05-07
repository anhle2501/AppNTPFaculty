package vn.bvntp.app.helper

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
// Function to encrypt and store data in SharedPreferences
fun storeEncryptedData(context: Context, key: String, value: String) {
    // Tạo khóa chính
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Khởi tạo EncryptedSharedPreferences
    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        "ACCESS_TOKEN",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Lưu trữ dữ liệu
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}

// Hàm lấy dữ liệu được mã hóa
fun retrieveAccessDataDecryptedData(context: Context, key:String): String? {
    // Tạo khóa chính
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Khởi tạo EncryptedSharedPreferences
    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        "ACCESS_TOKEN",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Lấy dữ liệu
    return sharedPreferences.getString(key, "")
}



fun hasAccessToken(context: Context) : Boolean{
    val accessToken = retrieveAccessDataDecryptedData(context,"access_token")
    Log.d("hasAccessToken", "a" + accessToken)
    return accessToken != null && accessToken != ""
}