/**************************************************************************************************
 * Copyright (c) 2013-2018 Ashutosh Kumar Singh <ashutosh@aksingh.net>                            *
 *                                                                                                *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this           *
 * software and associated documentation files (the "Software"), to deal in the Software without  *
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,     *
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the  *
 * Software is furnished to do so, subject to the following conditions:                           *
 *                                                                                                *
 * The above copyright notice and this permission notice shall be included in all copies or       *
 * substantial portions of the Software.                                                          *
 *                                                                                                *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING  *
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND     *
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,   *
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.        *
 **************************************************************************************************/

package net.aksingh.owmjapis.core

import net.aksingh.owmjapis.api.APIException
import net.aksingh.owmjapis.api.DailyWeatherForecastAPI
import net.aksingh.owmjapis.model.DailyWeatherForecast

/**
 * **Starting point for this lib.** If you're new to this API, start from this class.
 *
 * Lets you access data from OpenWeatherMap.org using its Weather APIs (Pro).
 *
 * **Sample code in Java:**
 * `OWMPro owm = new OWMPro("your-pro-api-key");`
 *
 * **Sample code in Kotlin:**
 * `val owm: OWMPro = OWMPro("your-pro-api-key")`
 *
 * [OpenWeatherMap.org](https://openweathermap.org/)
 * [OpenWeatherMap.org APIs](https://openweathermap.org/api/)
 *
 * @author Ashutosh Kumar Singh
 * @version 2018-09-25
 *
 * @since 2.5.2.3
 */
class OWMPro : OWM {

  companion object {
    private val OWM_PRO_V25_BASE_URL: String = "https://pro.openweathermap.org/data/2.5/"
    private val OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT: Int = 16
  }

  /**
   * Constructor
   *
   * Defaults: Search accuracy is set to like
   * Defaults: Unit is set to standard
   * Defaults: Language is set to English
   * Defaults: Proxy is set to system's proxy
   *
   * [OpenWeatherMap.org API key](https://openweathermap.org/appid)
   *
   * @param apiKey API key (pro) from OpenWeatherMap.org
   */
  constructor(apiKey: String) : super(apiKey, OWM_PRO_V25_BASE_URL)

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityName: String): DailyWeatherForecast {
    return dailyWeatherForecastByCityName(cityName, OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityName: String, count: Int): DailyWeatherForecast {
    val api = retrofit4weather.create(DailyWeatherForecastAPI::class.java)

    val apiCall = api.getDailyWeatherForecastByCityName(cityName, count)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = DailyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityName: String, countryCode: OWM.Country): DailyWeatherForecast {
    return dailyWeatherForecastByCityName(cityName, countryCode, OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityName: String, countryCode: OWM.Country, count: Int): DailyWeatherForecast {
    return dailyWeatherForecastByCityName(cityName + "," + countryCode.value, count)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityId(cityId: Int): DailyWeatherForecast {
    return dailyWeatherForecastByCityId(cityId, OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityId(cityId: Int, count: Int): DailyWeatherForecast {
    val api = retrofit4weather.create(DailyWeatherForecastAPI::class.java)

    val apiCall = api.getDailyWeatherForecastByCityId(cityId, count)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = DailyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCoords(latitude: Double, longitude: Double): DailyWeatherForecast {
    return dailyWeatherForecastByCoords(latitude, longitude, OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCoords(latitude: Double, longitude: Double, count: Int): DailyWeatherForecast {
    val api = retrofit4weather.create(DailyWeatherForecastAPI::class.java)

    val apiCall = api.getDailyWeatherForecastByCoords(latitude, longitude, count)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = DailyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int): DailyWeatherForecast {
    return dailyWeatherForecastByZipCode(zipCode, OWM.Country.UNITED_STATES, OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int, count: Int): DailyWeatherForecast {
    return dailyWeatherForecastByZipCode(zipCode, OWM.Country.UNITED_STATES, count)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int, countryCode: OWM.Country): DailyWeatherForecast {
    return dailyWeatherForecastByZipCode(zipCode, countryCode, OWM_PRO_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int, countryCode: OWM.Country, count: Int): DailyWeatherForecast {
    val api = retrofit4weather.create(DailyWeatherForecastAPI::class.java)

    val apiCall = api.getDailyWeatherForecastByZipCode(zipCode.toString() + "," + countryCode.value, count)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = DailyWeatherForecast()
    }

    return forecast
  }
}
