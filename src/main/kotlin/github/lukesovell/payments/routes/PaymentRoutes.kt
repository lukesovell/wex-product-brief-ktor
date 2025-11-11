package github.lukesovell.payments.routes

import github.lukesovell.payments.service.PaymentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.paymentsById() {
    val service by inject<PaymentService>()
    get("/payments/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Id parameter can't be null")
        call.respond(HttpStatusCode.OK, service.getByIdInCurrency(id, "USD"))
    }
}

fun Route.createPayment() {
    post("/payments") {
        call.respond(HttpStatusCode.Created)
    }
}

fun Application.paymentRoutes() {
    routing {
        paymentsById()
        createPayment()
    }
}