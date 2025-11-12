package github.lukesovell.payments.repository

import java.math.BigDecimal
import java.time.Instant

class PaymentRepositoryImpl : PaymentRepository {

    override fun getPayment(id: String): PaymentEntity {
        // select * from payment where id = {id};
        return PaymentEntity(
            id = id,
            "Test",
            Instant.now().toEpochMilli(),
            BigDecimal("100.00"),
            "USD"
        )
    }

    override fun createPayment(payment: PaymentEntity): PaymentEntity {
        // insert into payment (...) values (...);
        return payment
    }
}