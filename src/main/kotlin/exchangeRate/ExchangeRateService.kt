package github.lukesovell.exchangeRate

import java.math.BigDecimal

interface ExchangeRateService {

    /**
     * Converts the input USD dollar amount into the desired currency
     */
    fun convert(amount: BigDecimal, currency: String): BigDecimal
}