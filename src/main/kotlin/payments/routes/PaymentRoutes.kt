package github.lukesovell.payments.routes

import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import github.lukesovell.payments.service.CreatePaymentDto
import github.lukesovell.payments.service.PaymentService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.paymentsById() {
    val service by inject<PaymentService>()
    get("/payments/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Id parameter can't be null")
        val currency = call.request.queryParameters["currency"] ?: USD_CURRENCY_DESC
        call.respond(HttpStatusCode.OK, service.getByIdInCurrency(id, currency))
    }
}

fun Route.createPayment() {
    post("/payments") {
        val service by inject<PaymentService>()
        val payment = call.receive<CreatePaymentDto>()
        val createdPayment = service.createPayment(payment)
        call.respond(HttpStatusCode.Created, createdPayment)
    }
}