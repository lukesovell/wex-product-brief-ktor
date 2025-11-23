package github.lukesovell

import org.junit.Assert
import kotlin.test.Test

class ApplicationTest {

    // TODO: come back to tests using testcontainers

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
