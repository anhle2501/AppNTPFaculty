package vn.bvntp.app.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bvntp.app.api.HoSoBenhAnService
import vn.bvntp.app.helper.retrieveAccessDataDecryptedData
import vn.bvntp.app.model.LichSuDieuTriResponse
import vn.bvntp.app.model.RequestLichSuDieuTri
import vn.bvntp.app.model.Test
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.Reader
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit


class HoSoBenhAnRepository(val hoSoBenhAnService: HoSoBenhAnService) {

    suspend fun getHoSoBenhAn(
        context: Context,
        maVaoVien: String,
        listId: ArrayList<Int>,
        onResult: (Result<File>) -> Unit
    ) = withContext(Dispatchers.IO) {

        val accessToken = retrieveAccessDataDecryptedData(context, "access_token")
        val file = File(context.cacheDir, "tempFileHsba.pdf")
        if (file.isFile) file.delete()

        val client = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()

        val mediaType = "application/json".toMediaType()
        val body =
            "\n{\r\n    \"ListID\": $listId,\r\n    \"MaVaoVien\": \"$maVaoVien\"\r\n}".toRequestBody(
                mediaType
            )
        val request =
            Request.Builder().url("http://115.75.5.67:8089/api/hsba/LoadInVanBanKy").post(body)
                .addHeader("Authorization", accessToken ?: "")
                .addHeader("Content-Type", "application/json").build()

        try {
            val call = client.newCall(request)
            val response = call.execute().body
            val buffer = 2048
            val jsonString = BufferedInputStream( response?.byteStream(), buffer )
            val reader: Reader = InputStreamReader(jsonString, "UTF-8")

            val readerJsonReader = JsonReader(reader)

            val fileOutput = BufferedOutputStream(FileOutputStream(file), buffer)

            fun writePdfbyte(reader: JsonReader, writer: BufferedOutputStream) {
                reader.beginArray()
                while (reader.hasNext()) {
                    writer.write(reader.nextInt())
                }
                reader.endArray()
            }

            readerJsonReader.beginObject()
            while (readerJsonReader.hasNext()) {
                var name = readerJsonReader.nextName()
                if (name.equals("Data")) {
                    writePdfbyte(readerJsonReader, fileOutput)
                } else {
                    readerJsonReader.skipValue()
                }
            }
            readerJsonReader.endObject()
            reader.close()
            readerJsonReader.close()
            fileOutput.close()
            jsonString.close()
            response?.close()

            Log.d("file", file.absolutePath.toString())
            onResult(Result.success(file))
//            Log.d("response", response!!.string())
//            val byteSteam = response?.byteStream();
//
//            val contentLength = response?.contentLength();
//            Log.d("contentLength", contentLength.toString())
//            val outputStream = FileOutputStream(file);
//            val baos = ByteArrayOutputStream()
//
//
//            var n =-1
//            val buffer = ByteArray(4096);

//            var numberOfByte = 0.toLong();
//            while (contentLength != null) {
//                Log.d("in loop", "in loop")
//                // 1st read, dont read 38 byte
//                n = byteSteam!!.read(buffer, 0, buffer.size)
//                Log.d("n", n.toString())
//                if ( n == -1) {
//                    break;
//                }
//
//                if (numberOfByte == 0L) {
////                    baos.write(buffer, 38, n - 38)
//
//                    outputStream.write(buffer, 38, n - 38)
////                    outputStream.write(baos.toByteArray())
//
//                } else if ( (contentLength - numberOfByte) <= 4096.toLong() ){
////                    baos.write(buffer, 0, n - 10)
//                    outputStream.write(buffer, 0, n - 10)
////                    outputStream.write(baos.toByteArray())
//                } else {
////                    baos.write(buffer, 0, n)
//
//                    outputStream.write(buffer, 0, n)
////                    outputStream.write(baos.toByteArray())
//                }
//                numberOfByte += n
//
//
//                Log.d("end loop", "end loop")
//
//            }



//            byteSteam?.close()

//
//            if (byteStream != null) {
//                byteStream.close()
//            }

//            outputStream.close()
//
//            inputStream2.close()
//            outputStream2.close()

//            if (response.isSuccessful) {
//                Log.d("response.message", response.message)
//                Log.d("response", response.code.toString())
//                val a = response.body?.string()


//                val inputStream = response.body?.byteStream()
//                val bufferSize = 8 * 1024;
//                val reader = BufferedReader(InputStreamReader(inputStream), bufferSize)
//
//                var test = reader.lineSequence().iterator();
//                Log.d("test.hasNext()", test.hasNext().toString())
//                val test = reader.lineSequence().iterator();
//                while (test.hasNext()) {
//                    val line = test.next();
//                    Log.d("line", line)
//                }

//                reader.useLines {
//                    it.map { line ->
//                        Log.d("est", "test ")
//                        Log.d("line", line)}
//                }

//                reader.close()




//                val jsonObject = a?.let { JSONObject(it) };

//                inputStream?.close()
//                response?.close()


               /* if (jsonObject?.get("Data") != null) {
                    fun JSONArray.toByteArray(): ByteArray {
                        val byteArr = ByteArray(length())
                        for (i in 0 until length()) {
                            byteArr[i] = (get(i) as Int and 0xFF).toByte()
                        }
                        return byteArr
                    }

                    val jsonArray: ByteArray

                    if (jsonObject.getJSONArray("Data") != null) {
                        jsonArray = jsonObject.getJSONArray("Data").toByteArray()
                        FileOutputStream(file).use { outputStream ->
                            outputStream.write(
                                jsonArray
                            )
                            outputStream.close()
                            Log.d("End Thread", "End Thread")
                            Log.d("file", file.absolutePath.toString())
                            onResult(Result.success(file))
                        }
                    }
                }*/
//            } else {
//                onResult(Result.failure(Exception("Không nhận được phản hồi từ server")))
//            }
        } catch (e: IllegalStateException) {
            val j = IllegalStateException("Không có hồ sơ bệnh án!")
            onResult(Result.failure(j))

        } catch (e: OutOfMemoryError) {
            val j = OutOfMemoryError("Hết bộ nhớ !")
            onResult(Result.failure(j))
        } catch (e: JSONException) {

            val j = JSONException("Không có hồ sơ bệnh án!")
            onResult(Result.failure(j))
        } catch (e: InterruptedException) {

            val i = InterruptedException("Người dùng dừng tải tập tin !")
            onResult(Result.failure(i))
        } catch (e: Exception) {
            Log.d("e", e.toString())
            val n = Exception("Không nhận được dữ liệu từ server !")
            onResult((Result.failure(n)))
        } finally {

        }
        Log.d("end", "end")
    }


    fun getLichSuVaoVien(
        context: Context, maBenhNhan: String, onResult: (Result<LichSuDieuTriResponse>) -> Unit
    ) {
        val reqVanBanKy = RequestLichSuDieuTri(MaBN = maBenhNhan)

        val accessToken = retrieveAccessDataDecryptedData(context, "access_token")

        accessToken?.let {
            Log.d("accessToken getLichSuVaoVien", "accessToken: " + accessToken)
            hoSoBenhAnService.getLichSuDieuTriResponse(accessToken, reqVanBanKy)
                .enqueue(object : Callback<LichSuDieuTriResponse> {
                    override fun onResponse(
                        call: Call<LichSuDieuTriResponse>, response: Response<LichSuDieuTriResponse>
                    ) {
                        Log.d("getLichSuDieuTriResponse1", "getLichSuDieuTriResponse1")

                        if (response.isSuccessful ) {

                            val lichSuDieuTriResponse = response.body()
                            Log.d("getLichSuDieuTriResponse2", "getLichSuDieuTriResponse2")
                            lichSuDieuTriResponse?.let {
                                onResult(Result.success(it))
                            }
                        } else {
                            Log.d("getLichSuDieuTriResponse3", "getLichSuDieuTriResponse3")
                            onResult(
                                Result.failure(
                                    throw UnknownError(
                                        response.errorBody().toString()
                                    )
                                )
                            )

                        }
                    }
                    override fun onFailure(call: Call<LichSuDieuTriResponse>, t: Throwable) {
                        onResult(Result.failure(throw UnknownError("Unknown error")))
                    }

                })
        }


    }

}