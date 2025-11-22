package github.lukesovell.config

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.koin.dsl.module

fun Application.configureHTTP() {
    routing {
        swaggerUI(path = "openapi")
    }
}

val networkModule = module {
    single {
        HttpClient(CIO) {
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
        }
    }
}
