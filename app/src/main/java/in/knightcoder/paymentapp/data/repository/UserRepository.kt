package `in`.knightcoder.paymentapp.data.repository

import `in`.knightcoder.paymentapp.data.model.LoginResponse
import `in`.knightcoder.paymentapp.data.remote.ApiService
import `in`.knightcoder.paymentapp.utils.SharePrefManager
import javax.inject.Inject

/**
 * @Author: Amit Sarkar
 * @Date: 06-06-2024
 */

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val sharePrefManager: SharePrefManager,
) {
    suspend fun login(username: String, password: String): LoginResponse? {
        val response = apiService.login(hashMapOf("email" to username, "password" to password))
        if (response.isSuccessful && response.body()?.status == true) {
            response.body()?.token?.let { saveToken(it) }
        }
        return response.body()
    }

    private fun saveToken(token: String) {
        sharePrefManager.saveString("TOKEN_KEY", token)
    }
}
