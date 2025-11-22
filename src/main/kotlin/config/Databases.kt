package github.lukesovell.config

import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

// TODO add hikari for connection pooling
fun Application.configureDatabases() {
    Database.connect(
        environment.config.property("postgres.url").getString(),
        driver = "org.postgresql.Driver",
        user = environment.config.property("postgres.user").getString(),
        password = environment.config.property("postgres.password").getString()
    )
}

suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction { block() }
}