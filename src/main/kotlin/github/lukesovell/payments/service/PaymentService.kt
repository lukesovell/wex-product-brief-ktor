package github.lukesovell.payments.service

interface PaymentService {

    fun getByIdInCurrency(id: String, currency: String) : PaymentDto

    fun createPayment(payment: PaymentDto)
}