package github.lukesovell

import github.lukesovell.config.configureDatabases
import github.lukesovell.config.configureHTTP
import github.lukesovell.config.configureSerialization
import github.lukesovell.payments.routes.paymentRoutes
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
//    configureDatabases()
    paymentRoutes()
}
