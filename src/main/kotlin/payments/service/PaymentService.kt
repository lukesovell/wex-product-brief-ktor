package github.lukesovell.payments.service

interface PaymentService {

    suspend fun getByIdInCurrency(id: String, currency: String) : PaymentDto

    fun createPayment(payment: PaymentDto) : PaymentDto
}