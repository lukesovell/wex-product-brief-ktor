package github.lukesovell.payments.repository

import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class PaymentRepositoryImpl : PaymentRepository {

    override fun getPayment(id: String): PaymentEntity {
        val result = transaction {
            PaymentDAO.findById(UUID.fromString(id))
                ?.let { daoToEntity(it) }
        }

        if (result == null) {
            throw IllegalStateException("Payment with id $id not found")
        }

        return result
    }

    override fun createPayment(payment: PaymentEntity): PaymentEntity {
        val result = transaction {
            PaymentDAO.new(UUID.fromString(payment.id)) {
                description = payment.description
                transactionDate = payment.transactionDate
                purchaseAmount = payment.purchaseAmount
                currency = payment.currency
            }
        }

        return daoToEntity(result)
    }
}