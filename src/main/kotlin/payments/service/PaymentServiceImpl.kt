package github.lukesovell.payments.service

import github.lukesovell.exchangeRate.ExchangeRateService
import github.lukesovell.payments.repository.PaymentRepository
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class PaymentServiceImpl(
    val repository: PaymentRepository,
    val exchangeRateService: ExchangeRateService
) : PaymentService {

    override suspend fun getByIdInCurrency(id: String, currency: String): PaymentDto {
        val paymentDto = repository.getPayment(id)
        val convertedPurchaseAmt = getAmountInCurrency(
            BigDecimal(paymentDto.purchaseAmount),
            currency,
            paymentDto.transactionDate
        )

        return paymentDto.copy(
            purchaseAmount = convertedPurchaseAmt.toString(),
            currency = currency
        )
    }

    private suspend fun getAmountInCurrency(purchaseAmount: BigDecimal, currency: String, date: Long): BigDecimal {
        val dateString = epochToDateString(date)
        return exchangeRateService.convert(purchaseAmount, currency, dateString)
    }

    private fun epochToDateString(epochMillis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
            .let { formatter.format(it) }
    }

    override fun createPayment(payment: CreatePaymentDto): PaymentDto {
        return repository.createPayment(payment)
    }
}