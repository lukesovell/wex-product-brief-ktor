package github.lukesovell.payments.service

import github.lukesovell.payments.repository.PaymentEntity
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class PaymentDto(
    val id: String,
    val description: String,
    val transactionDate: Long,
    val purchaseAmount: String,
    val currency: String
) {

    fun toEntity(): PaymentEntity {
        return PaymentEntity(
            id,
            description,
            transactionDate,
            BigDecimal(purchaseAmount),
            currency)
    }
}