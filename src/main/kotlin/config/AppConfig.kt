package github.lukesovell.config

import org.jdbi.v3.core.Jdbi
import org.koin.dsl.module

data class AppConfig(
    val server: KtorServerConfig,
    val database: DatabaseConfig,
    val exchangeRates: ExchangeRateApiConfig
)

data class KtorServerConfig(val port: Int)

data class DatabaseConfig(
    val url: String,
    val user: String,
    val password: String
)

data class ExchangeRateApiConfig(val url: String)

fun configModule(config: AppConfig, jdbi: Jdbi) = module {
    single { config.database }
    single { config.exchangeRates }
    single { jdbi } // TODO This should go in a different module
}