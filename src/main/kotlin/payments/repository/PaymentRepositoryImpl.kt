package github.lukesovell.payments.repository

import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import github.lukesovell.payments.service.CreatePaymentDto
import github.lukesovell.payments.service.PaymentDto
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.mapTo
import org.jdbi.v3.core.kotlin.useHandleUnchecked
import org.jdbi.v3.core.kotlin.withHandleUnchecked
import java.math.BigDecimal
import java.util.*

class PaymentRepositoryImpl(val jdbi: Jdbi) : PaymentRepository {

    override fun getPayment(id: String): PaymentDto {
        val result = jdbi.withHandleUnchecked { handle ->
            handle.createQuery("SELECT * FROM payment WHERE id = :id")
                .bind("id", UUID.fromString(id))
                .mapTo<PaymentEntity>()
                .findOne()
        }

        if (result.isEmpty) {
            throw IllegalStateException("Payment with id $id not found")
        }

        return result.get().toDto()
    }

    override fun getPaymentsOverPurchaseAmountSinceDate(threshold: BigDecimal, sinceDate: Long): List<PaymentDto> {
        val results = jdbi.withHandleUnchecked { handle ->
            handle.createQuery("SELECT * FROM payment WHERE purchase_amount > :threshold and transaction_date > :sinceDate")
                .bind("threshold", threshold)
                .bind("sinceDate", sinceDate)
                .mapTo<PaymentEntity>()
                .list()
        }

        return results.map { it.toDto() }
    }

    override fun createPayment(payment: CreatePaymentDto): PaymentDto {
        val id = UUID.randomUUID()
        jdbi.useHandleUnchecked { handle ->
            handle.createUpdate(
                """INSERT INTO payment (id, description, transaction_date, purchase_amount, currency)
                 VALUES (:id, :description, :transactionDate, :purchaseAmount, :currency)""")
                .bind("id", id)
                .bind("description", payment.description)
                .bind("transactionDate", payment.transactionDate)
                .bind("purchaseAmount", payment.purchaseAmount.toFloat())
                .bind("currency", USD_CURRENCY_DESC)
                .execute()
        }

        return PaymentDto(id.toString(), payment.description, payment.transactionDate, payment.purchaseAmount, USD_CURRENCY_DESC)
    }
}