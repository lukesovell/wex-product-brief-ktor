package github.lukesovell.payments.service

import github.lukesovell.payments.repository.PaymentEntity
import java.math.BigDecimal

fun mapToDto(entity: PaymentEntity): PaymentDto {
    return PaymentDto(
        entity.id,
        entity.description,
        entity.transactionDate,
        entity.purchaseAmount.toString(),
        entity.currency
    )
}

fun mapToEntity(dto: PaymentDto): PaymentEntity {
    return PaymentEntity(
        dto.id,
        dto.description,
        dto.transactionDate,
        BigDecimal(dto.purchaseAmount),
        dto.currency
    )
}