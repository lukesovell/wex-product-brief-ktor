package github.lukesovell.config

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin

fun configureDatabases(config: DatabaseConfig) : Jdbi {
    return Jdbi.create(config.url, config.user, config.password)
        .also { it.installPlugin(KotlinPlugin()) }
}