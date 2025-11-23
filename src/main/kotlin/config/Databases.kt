package github.lukesovell.config

import org.jetbrains.exposed.sql.Database

fun configureDatabases(config: DatabaseConfig) {
    Database.connect(
        config.url,
        driver = "org.postgresql.Driver",
        user = config.user,
        password = config.password
    )
}