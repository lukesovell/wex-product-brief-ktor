package github.lukesovell.config

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

// TODO add hikari for connection pooling
fun Application.configureDatabases() {
    Database.connect(
        environment.config.property("postgres.url").getString(),
        driver = "org.postgresql.Driver",
        user = environment.config.property("postgres.user").getString(),
        password = environment.config.property("postgres.password").getString()
    )
}