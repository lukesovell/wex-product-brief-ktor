package github.lukesovell

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import github.lukesovell.config.AppConfig
import github.lukesovell.config.BigDecimalSerializer
import github.lukesovell.config.LocalDateSerializer
import github.lukesovell.config.configModule
import github.lukesovell.config.configureDatabases
import github.lukesovell.config.configureHTTP
import github.lukesovell.config.configureRoutes
import github.lukesovell.config.networkModule
import github.lukesovell.payments.di.paymentModule
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.koin.core.context.startKoin

fun main() {
    val appConfig = loadAppConfig() // Hoplite
    embeddedServer(Netty, port = appConfig.server.port) {
        module(appConfig)
    }.start(wait = true)
}

fun Application.module(config: AppConfig) {
    configureHTTP()
    configureRoutes()
    val dslContext = configureDatabases(config.database)

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
        // TODO: make database module for dslContext koin setup
        modules(configModule(config, dslContext), paymentModule, networkModule)
    }
}

fun loadAppConfig(): AppConfig {
    return ConfigLoaderBuilder.default()
        .addResourceSource("/application.yaml")
        .build()
        .loadConfigOrThrow<AppConfig>()
}
