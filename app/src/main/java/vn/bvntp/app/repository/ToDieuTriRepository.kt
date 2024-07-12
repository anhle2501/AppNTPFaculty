package vn.bvntp.app.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bvntp.app.api.ToDieuTriService
import vn.bvntp.app.model.ToDieuTri
import vn.bvntp.app.model.ToDieuTriRequest
import vn.bvntp.app.model.ToDieuTriResponse

class ToDieuTriRepository(val toDieuTriService: ToDieuTriService) {

    fun getDanhSachToDieuTri(accessToken: String, toDieuTriRequest: ToDieuTriRequest, onResult: (Result<ArrayList<ToDieuTri>>) -> Unit) {

        toDieuTriService.getDanhSachToDieuTri(accessToken, toDieuTriRequest).enqueue( object :
            Callback<ToDieuTriResponse> {
            override fun onResponse(
                call: Call<ToDieuTriResponse>,
                response: Response<ToDieuTriResponse>
            ) {
                if (response.isSuccessful) {
                    handleSuccess(response, onResult)
                } else {
                    handleError(response, onResult)
                }
            }

            override fun onFailure(call: Call<ToDieuTriResponse>, t: Throwable) {
                onResult(Result.failure(Exception(t)))
            }
        })
    }
    private fun handleSuccess(response: Response<ToDieuTriResponse>, onResult: (Result<ArrayList<ToDieuTri>>) -> Unit) {
        response.body()?.data?.let { body ->
            val dsToDieutri: ArrayList<ToDieuTri> = body
            onResult(Result.success(dsToDieutri))
        } ?: run {
            onResult(Result.failure(UnknownError("Response body is null")))
        }
    }

    private fun handleError(response: Response<ToDieuTriResponse>, onResult: (Result<ArrayList<ToDieuTri>>) -> Unit) {
        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
        onResult(Result.failure(UnknownError(errorMessage)))
    }

}

