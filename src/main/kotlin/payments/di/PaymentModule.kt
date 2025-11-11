package github.lukesovell.payments.di

import github.lukesovell.payments.service.PaymentService
import github.lukesovell.payments.service.PaymentServiceImpl
import org.koin.dsl.module

val paymentModule = module {
    single<PaymentService> { PaymentServiceImpl() }
}