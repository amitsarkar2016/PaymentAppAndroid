package `in`.knightcoder.paymentapp.data.model

/**
 * @Author: Amit Sarkar
 * @Date: 06-06-2024
 */
data class LoginResponse(
    val status: Boolean,
    val msg: String,
    val token: String,
)
