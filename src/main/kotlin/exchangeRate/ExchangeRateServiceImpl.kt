package github.lukesovell.exchangeRate

import github.lukesovell.payments.constant.USD_CURRENCY_DESC
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeRateServiceImpl(val client: HttpClient) : ExchangeRateService {

    // TODO make this configurable
    val baseUrl = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange"

    override suspend fun convert(amount: BigDecimal, currency: String, date: String): BigDecimal {
        if (currency == USD_CURRENCY_DESC) {
            return amount
        }

        val exchangeRate = getExchangeRateForDate(currency, date)
        return (amount * exchangeRate).setScale(2, RoundingMode.HALF_UP)
    }

    private suspend fun getExchangeRateForDate(currency: String, date: String): BigDecimal {
        val response = client.get(baseUrl) {
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