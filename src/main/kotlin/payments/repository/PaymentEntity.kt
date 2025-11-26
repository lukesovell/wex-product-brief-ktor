package github.lukesovell.payments.repository

import github.lukesovell.payments.service.PaymentDto
import java.math.BigDecimal
import java.util.UUID

data class PaymentEntity(val id: UUID,
                         val description: String,
                         val transactionDate: Long,
                         val purchaseAmount: BigDecimal,
                         val currency: String)

fun PaymentEntity.toDto(): PaymentDto {
    return PaymentDto(
        this.id.toString(),
        this.description,
        this.transactionDate,
        this.purchaseAmount.toString(),
        this.currency
    )
}