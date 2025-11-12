package github.lukesovell.payments.di

import github.lukesovell.payments.repository.PaymentRepository
import github.lukesovell.payments.repository.PaymentRepositoryImpl
import github.lukesovell.payments.service.PaymentService
import github.lukesovell.payments.service.PaymentServiceImpl
import org.koin.dsl.module

val paymentModule = module {
    single<PaymentRepository> { PaymentRepositoryImpl() }
    single<PaymentService> { PaymentServiceImpl(get()) }
}