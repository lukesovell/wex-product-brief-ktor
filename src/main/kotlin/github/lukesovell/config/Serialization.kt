package github.lukesovell.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

//TODO: what is this actually doing?
fun Application.configureSerialization() {
    routing {
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
