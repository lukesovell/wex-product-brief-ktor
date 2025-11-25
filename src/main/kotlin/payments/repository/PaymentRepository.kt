package github.lukesovell.payments.repository

import github.lukesovell.payments.service.CreatePaymentDto
import github.lukesovell.payments.service.PaymentDto

interface PaymentRepository {

    fun getPayment(id: String): PaymentDto

    fun createPayment(payment: CreatePaymentDto): PaymentDto
}