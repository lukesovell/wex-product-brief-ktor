package github.lukesovell.payments.repository

import github.lukesovell.payments.service.PaymentDto
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import java.util.*

object PaymentTable : IdTable<UUID>("payment") {
    override val id = uuid("id").autoGenerate().entityId()
    val description = varchar("description", 50)
    val transactionDate = long("transaction_date")
    val purchaseAmount = decimal("purchase_amount", 15, 2)
    val currency = varchar("currency", 50)

    override val primaryKey = PrimaryKey(id)
}

class PaymentEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<PaymentEntity>(PaymentTable)

    var description by PaymentTable.description
    var transactionDate by PaymentTable.transactionDate
    var purchaseAmount by PaymentTable.purchaseAmount
    var currency by PaymentTable.currency
}

fun PaymentEntity.toDto(): PaymentDto {
    return PaymentDto(
        this.id.toString(),
        this.description,
        this.transactionDate,
        this.purchaseAmount.toString(),
        this.currency
    )
}