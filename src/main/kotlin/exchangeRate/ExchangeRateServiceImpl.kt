package github.lukesovell.exchangeRate

import github.lukesovell.config.ExchangeRateApiConfig
import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeRateServiceImpl(val config: ExchangeRateApiConfig,val client: HttpClient) : ExchangeRateService {

    override suspend fun convert(amount: BigDecimal, currency: String, date: String): BigDecimal {
        if (currency == USD_CURRENCY_DESC) {
            return amount
        }

        val exchangeRate = getExchangeRateForDate(currency, date)
        return (amount * exchangeRate).setScale(2, RoundingMode.HALF_UP)
    }

    private suspend fun getExchangeRateForDate(currency: String, date: String): BigDecimal {
        val response = client.get(config.url) {
            url {
                parameter("filter", "country_currency_desc:eq:$currency,record_date:lt:$date")
                parameter("sort", "-record_date")
            }
        }.body<ExchangeRateResponse>()

        val latestRate = response.data.firstOrNull()
            ?: throw IllegalStateException("No exchange rate found for $currency before $date")

        return latestRate.exchangeRate
    }
}