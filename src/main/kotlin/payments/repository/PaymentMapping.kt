package github.lukesovell.payments.repository

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object PaymentTable : IdTable<UUID>("payment") {
    override val id = uuid("id").entityId()
    val description = varchar("description", 50)
    val transactionDate = long("transaction_date")
    val purchaseAmount = decimal("purchase_amount", 15, 2)
    val currency = varchar("currency", 50)

    override val primaryKey = PrimaryKey(id)
}

class PaymentDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<PaymentDAO>(PaymentTable)

    var description by PaymentTable.description
    var transactionDate by PaymentTable.transactionDate
    var purchaseAmount by PaymentTable.purchaseAmount
    var currency by PaymentTable.currency
}

fun daoToEntity(dao: PaymentDAO) = PaymentEntity(
    dao.id.toString(),
    dao.description,
    dao.transactionDate,
    dao.purchaseAmount,
    dao.currency,
)

