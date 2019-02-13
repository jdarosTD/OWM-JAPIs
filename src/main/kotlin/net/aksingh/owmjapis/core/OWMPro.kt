/**************************************************************************************************
 * Copyright (c) 2013-2019 Ashutosh Kumar Singh <ashutosh@aksingh.net>                            *
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
import net.aksingh.owmjapis.api.AccumulatedWeatherAPI
import net.aksingh.owmjapis.api.DailyWeatherForecastAPI
import net.aksingh.owmjapis.api.HistoricalWeatherAPI
import net.aksingh.owmjapis.model.AccumulatedWeatherList
import net.aksingh.owmjapis.model.DailyWeatherForecast
import net.aksingh.owmjapis.model.HistoricalWeatherList
import java.util.*

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
    private val OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT: Int = 1

    internal val OWM_PRO_V25_HISTORY_URL: String = "https://history.openweathermap.org/data/2.5/history/"
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
  fun accumulatedPrecipitationByCityId(cityId: Int, startTime: Long, endTime: Long): AccumulatedWeatherList {
    val api = retrofit4history.create(AccumulatedWeatherAPI::class.java)

    val apiCall = api.getAccumulatedPrecipByCityId(cityId, startTime, endTime)
    val apiResp = apiCall.execute()
    var accumulatedPrecip = apiResp.body()

    if (accumulatedPrecip == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      accumulatedPrecip = AccumulatedWeatherList()
    }

    return accumulatedPrecip
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCityId(cityId: Int, startTime: Date, endTime: Date): AccumulatedWeatherList {
    return accumulatedPrecipitationByCityId(cityId, startTime.time / 1000, endTime.time / 1000)
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCityName(cityNameWithCountryCode: String, startTime: Long, endTime: Long): AccumulatedWeatherList {
    val api = retrofit4history.create(AccumulatedWeatherAPI::class.java)

    val apiCall = api.getAccumulatedPrecipByCityName(cityNameWithCountryCode, startTime, endTime)
    val apiResp = apiCall.execute()
    var accumulatedPrecip = apiResp.body()

    if (accumulatedPrecip == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      accumulatedPrecip = AccumulatedWeatherList()
    }

    return accumulatedPrecip
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCityName(cityNameWithCountryCode: String, startTime: Date, endTime: Date): AccumulatedWeatherList {
    return accumulatedPrecipitationByCityName(cityNameWithCountryCode, startTime.time / 1000, endTime.time / 1000)
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCityName(cityName: String, countryCode: OWM.Country, startTime: Long, endTime: Long): AccumulatedWeatherList {
    return accumulatedPrecipitationByCityName(cityName + "," + countryCode.value, startTime, endTime)
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCityName(cityName: String, countryCode: OWM.Country, startTime: Date, endTime: Date): AccumulatedWeatherList {
    return accumulatedPrecipitationByCityName(cityName + "," + countryCode.value, startTime.time / 1000, endTime.time / 1000)
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCoords(latitude: Double, longitude: Double, startTime: Long, endTime: Long): AccumulatedWeatherList {
    val api = retrofit4history.create(AccumulatedWeatherAPI::class.java)

    val apiCall = api.getAccumulatedPrecipByCoords(latitude, longitude, startTime, endTime)
    val apiResp = apiCall.execute()
    var accumulatedPrecip = apiResp.body()

    if (accumulatedPrecip == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      accumulatedPrecip = AccumulatedWeatherList()
    }

    return accumulatedPrecip
  }

  @Throws(APIException::class)
  fun accumulatedPrecipitationByCoords(latitude: Double, longitude: Double, startTime: Date, endTime: Date): AccumulatedWeatherList {
    return accumulatedPrecipitationByCoords(latitude, longitude, startTime.time / 1000, endTime.time / 1000)
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCityId(cityId: Int, startTime: Long, endTime: Long, threshold: Int): AccumulatedWeatherList {
    val api = retrofit4history.create(AccumulatedWeatherAPI::class.java)

    val apiCall = api.getAccumulatedTempByCityId(cityId, startTime, endTime, threshold)
    val apiResp = apiCall.execute()
    var accumulatedTemp = apiResp.body()

    if (accumulatedTemp == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      accumulatedTemp = AccumulatedWeatherList()
    }

    return accumulatedTemp
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCityId(cityId: Int, startTime: Date, endTime: Date, threshold: Int): AccumulatedWeatherList {
    return accumulatedTemperatureByCityId(cityId, startTime.time / 1000, endTime.time / 1000, threshold)
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCityName(cityNameWithCountryCode: String, startTime: Long, endTime: Long, threshold: Int): AccumulatedWeatherList {
    val api = retrofit4history.create(AccumulatedWeatherAPI::class.java)

    val apiCall = api.getAccumulatedTempByCityName(cityNameWithCountryCode, startTime, endTime, threshold)
    val apiResp = apiCall.execute()
    var accumulatedTemp = apiResp.body()

    if (accumulatedTemp == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      accumulatedTemp = AccumulatedWeatherList()
    }

    return accumulatedTemp
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCityName(cityNameWithCountryCode: String, startTime: Date, endTime: Date, threshold: Int): AccumulatedWeatherList {
    return accumulatedTemperatureByCityName(cityNameWithCountryCode, startTime.time / 1000, endTime.time / 1000, threshold)
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCityName(cityName: String, countryCode: OWM.Country, startTime: Long, endTime: Long, threshold: Int): AccumulatedWeatherList {
    return accumulatedTemperatureByCityName(cityName + "," + countryCode.value, startTime, endTime, threshold)
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCityName(cityName: String, countryCode: OWM.Country, startTime: Date, endTime: Date, threshold: Int): AccumulatedWeatherList {
    return accumulatedTemperatureByCityName(cityName + "," + countryCode.value, startTime.time / 1000, endTime.time / 1000, threshold)
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCoords(latitude: Double, longitude: Double, startTime: Long, endTime: Long, threshold: Int): AccumulatedWeatherList {
    val api = retrofit4history.create(AccumulatedWeatherAPI::class.java)

    val apiCall = api.getAccumulatedTempByCoords(latitude, longitude, startTime, endTime, threshold)
    val apiResp = apiCall.execute()
    var accumulatedTemp = apiResp.body()

    if (accumulatedTemp == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      accumulatedTemp = AccumulatedWeatherList()
    }

    return accumulatedTemp
  }

  @Throws(APIException::class)
  fun accumulatedTemperatureByCoords(latitude: Double, longitude: Double, startTime: Date, endTime: Date, threshold: Int): AccumulatedWeatherList {
    return accumulatedTemperatureByCoords(latitude, longitude, startTime.time / 1000, endTime.time / 1000, threshold)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityNameWithCountryCode: String, count: Int): DailyWeatherForecast {
    val api = retrofit4weather.create(DailyWeatherForecastAPI::class.java)

    val apiCall = api.getDailyWeatherForecastByCityName(cityNameWithCountryCode, count)
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

  @Throws(APIException::class)
  fun historicalWeatherByCityId(cityId: Int, type: String, startTime: Long, endTime: Long, count: Int): HistoricalWeatherList {
    val api = retrofit4history.create(HistoricalWeatherAPI::class.java)

    val apiCall = api.getHistoricalWeatherByCityId(cityId, type, startTime, endTime, count)
    val apiResp = apiCall.execute()
    var historicalWeather = apiResp.body()

    if (historicalWeather == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      historicalWeather = HistoricalWeatherList()
    }

    return historicalWeather
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityId(cityId: Int, type: String, startTime: Date, endTime: Date, count: Int): HistoricalWeatherList {
    return historicalWeatherByCityId(cityId, type, startTime.time / 1000, endTime.time / 1000, count)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityId(cityId: Int, type: String, startTime: Long, endTime: Long): HistoricalWeatherList {
    return historicalWeatherByCityId(cityId, type, startTime, endTime, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityId(cityId: Int, type: String, startTime: Date, endTime: Date): HistoricalWeatherList {
    return historicalWeatherByCityId(cityId, type, startTime.time / 1000, endTime.time / 1000, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityNameWithCountryCode: String, type: String, startTime: Long, endTime: Long, count: Int): HistoricalWeatherList {
    val api = retrofit4history.create(HistoricalWeatherAPI::class.java)

    val apiCall = api.getHistoricalWeatherByCityName(cityNameWithCountryCode, type, startTime, endTime, count)
    val apiResp = apiCall.execute()
    var historicalWeather = apiResp.body()

    if (historicalWeather == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      historicalWeather = HistoricalWeatherList()
    }

    return historicalWeather
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityNameWithCountryCode: String, type: String, startTime: Date, endTime: Date, count: Int): HistoricalWeatherList {
    return historicalWeatherByCityName(cityNameWithCountryCode, type, startTime.time / 1000, endTime.time / 1000, count)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityNameWithCountryCode: String, type: String, startTime: Long, endTime: Long): HistoricalWeatherList {
    return historicalWeatherByCityName(cityNameWithCountryCode, type, startTime, endTime, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityNameWithCountryCode: String, type: String, startTime: Date, endTime: Date): HistoricalWeatherList {
    return historicalWeatherByCityName(cityNameWithCountryCode, type, startTime.time / 1000, endTime.time / 1000, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityName: String, countryCode: OWM.Country, type: String, startTime: Long, endTime: Long, count: Int): HistoricalWeatherList {
    return historicalWeatherByCityName(cityName + "," + countryCode.value, type, startTime, endTime, count)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityName: String, countryCode: OWM.Country, type: String, startTime: Date, endTime: Date, count: Int): HistoricalWeatherList {
    return historicalWeatherByCityName(cityName + "," + countryCode.value, type, startTime.time / 1000, endTime.time / 1000, count)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityName: String, countryCode: OWM.Country, type: String, startTime: Long, endTime: Long): HistoricalWeatherList {
    return historicalWeatherByCityName(cityName + "," + countryCode.value, type, startTime, endTime, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCityName(cityName: String, countryCode: OWM.Country, type: String, startTime: Date, endTime: Date): HistoricalWeatherList {
    return historicalWeatherByCityName(cityName + "," + countryCode.value, type, startTime.time / 1000, endTime.time / 1000, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCoords(latitude: Double, longitude: Double, type: String, startTime: Long, endTime: Long, count: Int): HistoricalWeatherList {
    val api = retrofit4history.create(HistoricalWeatherAPI::class.java)

    val apiCall = api.getHistoricalWeatherByCoords(latitude, longitude, type, startTime, endTime, count)
    val apiResp = apiCall.execute()
    var historicalWeather = apiResp.body()

    if (historicalWeather == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      historicalWeather = HistoricalWeatherList()
    }

    return historicalWeather
  }

  @Throws(APIException::class)
  fun historicalWeatherByCoords(latitude: Double, longitude: Double, type: String, startTime: Date, endTime: Date, count: Int): HistoricalWeatherList {
    return historicalWeatherByCoords(latitude, longitude, type, startTime.time / 1000, endTime.time / 1000, count)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCoords(latitude: Double, longitude: Double, type: String, startTime: Long, endTime: Long): HistoricalWeatherList {
    return historicalWeatherByCoords(latitude, longitude, type, startTime, endTime, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }

  @Throws(APIException::class)
  fun historicalWeatherByCoords(latitude: Double, longitude: Double, type: String, startTime: Date, endTime: Date): HistoricalWeatherList {
    return historicalWeatherByCoords(latitude, longitude, type, startTime.time / 1000, endTime.time / 1000, OWM_PRO_V25_HISTORICAL_WEATHER_DEFAULT_COUNT)
  }
}
