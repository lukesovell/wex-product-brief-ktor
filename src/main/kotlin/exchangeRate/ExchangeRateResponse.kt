package github.lukesovell.exchangeRate

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class ExchangeRateResponse(
    val data: List<ExchangeRate>
)

@Serializable
data class ExchangeRate(
    @SerialName("country_currency_desc") val countryCurrencyDesc: String,
    @Contextual @SerialName("exchange_rate") val exchangeRate: BigDecimal,
    @Contextual @SerialName("record_date") val recordDate: LocalDate
)