package github.lukesovell

import github.lukesovell.config.BigDecimalSerializer
import github.lukesovell.config.LocalDateSerializer
import github.lukesovell.config.configureDatabases
import github.lukesovell.config.configureHTTP
import github.lukesovell.config.configureRoutes
import github.lukesovell.config.networkModule
import github.lukesovell.payments.di.paymentModule
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureDatabases()
    configureRoutes()

    install(ContentNegotiation) {
        json(Json {
            serializersModule = SerializersModule {
                contextual(BigDecimalSerializer)
                contextual(LocalDateSerializer)
            }
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    startKoin {
        modules(paymentModule, networkModule)
    }
}
