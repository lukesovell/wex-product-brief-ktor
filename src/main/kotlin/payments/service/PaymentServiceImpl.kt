package github.lukesovell.payments.service

import github.lukesovell.exchangeRate.ExchangeRateService
import github.lukesovell.payments.repository.PaymentRepository
import java.math.BigDecimal

class PaymentServiceImpl(
    val repository: PaymentRepository,
    val exchangeRateService: ExchangeRateService
) : PaymentService {

    override fun getByIdInCurrency(
        id: String,
        currency: String
    ): PaymentDto {
        val paymentEntity = repository.getPayment(id)
        val convertedPurchaseAmt = getAmountInCurrency(paymentEntity.purchaseAmount, currency)

        val dtoInUsd = mapToDto(paymentEntity)
        return dtoInUsd.copy(purchaseAmount = convertedPurchaseAmt.toString(), currency = currency)
    }

    fun getAmountInCurrency(purchaseAmount: BigDecimal, currency: String): BigDecimal {
        return exchangeRateService.convert(purchaseAmount, currency)
    }

    override fun createPayment(payment: PaymentDto): PaymentDto {
        val entity = mapToEntity(payment)
        repository.createPayment(entity)
        return mapToDto(entity)
    }
}