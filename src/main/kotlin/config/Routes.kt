package github.lukesovell.config

import github.lukesovell.payments.routes.*
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.configureRoutes() {
    routing {
        paymentsById()
        createPayment()
    }
}