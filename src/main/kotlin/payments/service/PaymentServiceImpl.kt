package github.lukesovell.payments.service

import java.time.Instant

class PaymentServiceImpl : PaymentService {

    override fun getByIdInCurrency(
        id: String,
        currency: String
    ): PaymentDto {
        return PaymentDto(
            id,
            "Test",
            Instant.now().toEpochMilli(),
            "100.00",
            currency
        )
    }

    override fun createPayment(payment: PaymentDto): PaymentDto {
        return payment
    }
}