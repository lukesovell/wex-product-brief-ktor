package github.lukesovell.payments.repository

import github.lukesovell.payments.service.CreatePaymentDto
import github.lukesovell.payments.service.PaymentDto
import java.math.BigDecimal

interface PaymentRepository {

    fun getPayment(id: String): PaymentDto

    // Not used, random query just for fun :)
    fun getPaymentsOverPurchaseAmountInCurrency(threshold: BigDecimal, sinceDate: Long) : List<PaymentDto>

    fun createPayment(payment: CreatePaymentDto): PaymentDto
}