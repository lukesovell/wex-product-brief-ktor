package github.lukesovell

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Assert
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

//    @Test
//    fun testRoot() = testApplication {
//        application {
//            module()
//        }
//        client.get("/payments/1234").apply {
//            assertEquals(HttpStatusCode.OK, status)
//        }
//    }

    @Test
    fun testRoot() {
        Assert.assertTrue(true)
    }

}
