plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("nu.studer.jooq") version "9.0"
}

group = "github.lukesovell"
version = "0.0.1"

application {
    mainClass = "github.lukesovell.ApplicationKt"
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.cn)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.hoplite.core)
    implementation(libs.hoplite.yaml)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.ktor)
    implementation(libs.koin.core)
    implementation(libs.postgresql)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("com.zaxxer:HikariCP:5.1.0")
    jooqGenerator("org.postgresql:postgresql:42.7.8")
    jooqGenerator("org.jooq:jooq-meta-extensions:3.18.4")
}

kotlin {
    jvmToolchain(21)
}



jooq {
    version.set("3.18.4")

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"

                    database.apply {
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        properties.add(
                            org.jooq.meta.jaxb.Property().withKey("scripts").withValue("src/main/resources/migrations")
                        )
                    }

                    generate.apply {
                        isDeprecated = false
                        isKotlinSetterJvmNameAnnotationsOnIsPrefix = true
                        isPojosAsKotlinDataClasses = true
                        isFluentSetters = true
                    }

                    target.apply {
                        packageName = "github.lukesovell.models"
                        directory = "build/generated-src/jooq/main"
                    }

                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

