package github.lukesovell.payments.service

import kotlinx.serialization.Serializable

@Serializable
data class PaymentDto(
    val id: String,
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: String,
    val currency: String
)

@Serializable
data class CreatePaymentDto(
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: String
)