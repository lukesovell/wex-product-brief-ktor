package github.lukesovell.exchangeRate

import io.ktor.client.HttpClient
import java.math.BigDecimal
import java.math.RoundingMode

class ExchangeRateServiceImpl(client: HttpClient) : ExchangeRateService {

    override fun convert(amount: BigDecimal, currency: String): BigDecimal {
        if (currency == "USD") {
            return amount
        }

        val exchangeRate = BigDecimal(0.8)
        return (amount * exchangeRate).setScale(2, RoundingMode.HALF_UP)
    }
}