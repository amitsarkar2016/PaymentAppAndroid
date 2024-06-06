package `in`.knightcoder.paymentapp.data.remote

import `in`.knightcoder.paymentapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST(UrlHelper.login)
    suspend fun login(@FieldMap hashMap: HashMap<String, String>): Response<LoginResponse>

}