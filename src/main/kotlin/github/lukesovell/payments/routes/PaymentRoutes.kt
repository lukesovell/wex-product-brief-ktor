package github.lukesovell.payments.routes

import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Route.paymentsById() {
    get("/payments/{id}") {
        call.respondText(text = "You called the payments API!")
    }
}

fun Route.createPayment() {
    post("/payments") {
        call.respondText(text = "You called the payments API!")
    }
}

fun Application.paymentRoutes() {
    routing {
        paymentsById()
        createPayment()
    }
}