package github.lukesovell.payments.repository

import github.lukesovell.models.tables.Payment.Companion.PAYMENT
import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import github.lukesovell.payments.service.CreatePaymentDto
import github.lukesovell.payments.service.PaymentDto
import org.jooq.DSLContext
import java.math.BigDecimal
import java.util.*

class PaymentRepositoryImpl(val dsl: DSLContext) : PaymentRepository {

    override fun getPayment(id: String): PaymentDto {
        val result = dsl.selectFrom(PAYMENT)
            .where(PAYMENT.ID.eq(UUID.fromString(id)))
            .fetchOneInto(PaymentEntity::class.java)

        if (result == null) {
            throw IllegalStateException("Payment with id $id not found")
        }

        return result.toDto()
    }

    override fun getPaymentsOverPurchaseAmountSinceDate(threshold: BigDecimal, sinceDate: Long): List<PaymentDto> {
        val results = dsl.selectFrom(PAYMENT)
            .where(
                PAYMENT.PURCHASE_AMOUNT.greaterThan(threshold)
                    .and(PAYMENT.TRANSACTION_DATE.greaterThan(sinceDate))
            )
            .fetchInto(PaymentEntity::class.java)


        return results.map { it.toDto() }
    }

    override fun createPayment(payment: CreatePaymentDto): PaymentDto {
        val id = UUID.randomUUID()
        dsl.insertInto(PAYMENT)
            .set(PAYMENT.ID, id)
            .set(PAYMENT.DESCRIPTION, payment.description)
            .set(PAYMENT.PURCHASE_AMOUNT, BigDecimal(payment.purchaseAmount))
            .set(PAYMENT.TRANSACTION_DATE, payment.transactionDate)
            .set(PAYMENT.CURRENCY, USD_CURRENCY_DESC)
            .execute()

        return PaymentDto(
            id.toString(),
            payment.description,
            payment.transactionDate,
            payment.purchaseAmount,
            USD_CURRENCY_DESC
        )
    }
}