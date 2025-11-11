package github.lukesovell.payments.repository

import java.math.BigDecimal

data class PaymentEntity(
    val id: String,
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: BigDecimal,
    val currency: String
)