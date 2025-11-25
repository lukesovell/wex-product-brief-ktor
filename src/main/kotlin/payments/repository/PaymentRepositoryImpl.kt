package github.lukesovell.payments.repository

import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import github.lukesovell.payments.service.CreatePaymentDto
import github.lukesovell.payments.service.PaymentDto
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigDecimal
import java.util.*

class PaymentRepositoryImpl : PaymentRepository {

    override fun getPayment(id: String): PaymentDto {
        val result = transaction {
            PaymentEntity.findById(UUID.fromString(id))
        }

        if (result == null) {
            throw IllegalStateException("Payment with id $id not found")
        }

        return result.toDto()
    }

    override fun createPayment(payment: CreatePaymentDto): PaymentDto {
        val result = transaction {
            PaymentEntity.new {
                description = payment.description
                transactionDate = payment.transactionDate
                purchaseAmount = BigDecimal(payment.purchaseAmount)
                currency = USD_CURRENCY_DESC
            }
        }

        return result.toDto()
    }
}