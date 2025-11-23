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

    override suspend fun getByIdInCurrency(
        id: String,
        currency: String
    ): PaymentDto {
        val paymentEntity = repository.getPayment(id)
        val convertedPurchaseAmt = getAmountInCurrency(
            paymentEntity.purchaseAmount,
            currency,
            paymentEntity.transactionDate
        )

        val dtoInUsd = mapToDto(paymentEntity)
        return dtoInUsd.copy(purchaseAmount = convertedPurchaseAmt.toString(), currency = currency)
    }

    private suspend fun getAmountInCurrency(purchaseAmount: BigDecimal, currency: String, date: Long): BigDecimal {
        val dateString = epochToDateString(date)
        return exchangeRateService.convert(purchaseAmount, currency, dateString)
    }

    private fun epochToDateString(epochMillis: Long): String {
        val instant = Instant.ofEpochMilli(epochMillis)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return formatter.format(zonedDateTime)
    }

    override fun createPayment(payment: PaymentDto): PaymentDto {
        val entity = mapToEntity(payment)
        repository.createPayment(entity)
        return mapToDto(entity)
    }
}