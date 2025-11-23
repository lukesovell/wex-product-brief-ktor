package github.lukesovell.payments.service

import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class PaymentDto(
    val id: String,
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: String,
    val currency: String
) {

    constructor(createDto: CreatePaymentDto) : this(
        UUID.randomUUID().toString(),
        createDto.description,
        createDto.transactionDate,
        createDto.purchaseAmount,
        USD_CURRENCY_DESC
    )
}

@Serializable
data class CreatePaymentDto(
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: String
)