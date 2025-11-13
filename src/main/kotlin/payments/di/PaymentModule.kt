package github.lukesovell.payments.di

import github.lukesovell.exchangeRate.ExchangeRateService
import github.lukesovell.exchangeRate.ExchangeRateServiceImpl
import github.lukesovell.payments.repository.PaymentRepository
import github.lukesovell.payments.repository.PaymentRepositoryImpl
import github.lukesovell.payments.service.PaymentService
import github.lukesovell.payments.service.PaymentServiceImpl
import org.koin.dsl.module

val paymentModule = module {
    single<ExchangeRateService> { ExchangeRateServiceImpl(get()) }
    single<PaymentRepository> { PaymentRepositoryImpl() }
    single<PaymentService> { PaymentServiceImpl(get(), get()) }
}