package github.lukesovell.payments.repository

interface PaymentRepository {

    fun getPayment(id: String): PaymentEntity

    fun createPayment(payment: PaymentEntity): PaymentEntity
}