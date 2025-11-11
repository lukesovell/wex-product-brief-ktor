package github.lukesovell.payments.service

import github.lukesovell.payments.repository.PaymentEntity
import kotlinx.serialization.Serializable
import java.math.BigDecimal
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
        "USD"
    )

    fun toEntity(): PaymentEntity {
        return PaymentEntity(
            id,
            description,
            transactionDate,
            BigDecimal(purchaseAmount),
            currency
        )
    }
}

@Serializable
data class CreatePaymentDto(
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: String
)