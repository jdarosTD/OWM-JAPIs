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

import com.google.gson.GsonBuilder
import net.aksingh.owmjapis.api.*
import net.aksingh.owmjapis.model.*
import net.aksingh.owmjapis.util.OkHttpTools
import net.aksingh.owmjapis.util.SystemTools
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.*


/**
 * **Starting point for this lib.** If you're new to this API, start from this class.
 *
 * Lets you access data from OpenWeatherMap.org using its Weather APIs.
 *
 * **Sample code in Java:**
 * `OWM owm = new OWM("your-api-key");`
 *
 * **Sample code in Kotlin:**
 * `val owm: OWM = OWM("your-api-key")`
 *
 * [OpenWeatherMap.org](https://openweathermap.org/)
 * [OpenWeatherMap.org APIs](https://openweathermap.org/api/)
 *
 * @author Ashutosh Kumar Singh
 * @version 2017-11-07
 *
 * @since 2.5.1.0
 */
class OWM {

  private val OWM_DATA_V25_BASE_URL: String = "https://api.openweathermap.org/data/2.5/"
  private val OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT: Int = 16
  private val OWM_DATA_V25_HISTORICAL_UV_INDEX_MAX_COUNT: Int = 5

  private val OWM_POLLUTION_V10_BASE_URL: String = "https://api.openweathermap.org/pollution/v1/"

  private var apiKey: String
    set(value) {
      if (value.isEmpty() || value.isBlank()) {
        throw IllegalArgumentException("API key can't be empty/blank. Kindly get an API key from OpenWeatherMap.org")
      }

      field = value
    }

  private var accuracy: OWM.Accuracy
  private var unit: OWM.Unit
  private var lang: OWM.Language
  private var proxy: Proxy

  private var retrofit4weather: Retrofit
  private var retrofit4pollution: Retrofit
  private var retrofit4other: Retrofit

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
   * @param apiKey API key from OpenWeatherMap.org
   */
  constructor(apiKey: String) {
    this.apiKey = apiKey

    this.accuracy = OWM.Accuracy.LIKE
    this.unit = OWM.Unit.STANDARD
    this.lang = OWM.Language.ENGLISH
    this.proxy = SystemTools.getSystemProxy()

    this.retrofit4weather = createRetrofit4WeatherInstance(this.proxy)
    this.retrofit4pollution = createRetrofit4PollutionInstance(this.proxy)
    this.retrofit4other = createRetrofit4OtherInstance(this.proxy)
  }

  /**
   * Set search accuracy for getting data from OpenWeatherMap.org
   *
   * @param accuracy Search accuracy
   */
  fun setAccuracy(accuracy: OWM.Accuracy): OWM {
    this.accuracy = accuracy
    this.retrofit4weather = createRetrofit4WeatherInstance(this.proxy)

    return this
  }

  /**
   * Set unit for getting data from OpenWeatherMap.org
   *
   * @param unit Unit
   */
  fun setUnit(unit: OWM.Unit): OWM {
    this.unit = unit
    this.retrofit4weather = createRetrofit4WeatherInstance(this.proxy)

    return this
  }

  /**
   * Set language for getting data from OpenWeatherMap.org
   *
   * @param lang Language
   */
  fun setLanguage(lang: OWM.Language): OWM {
    this.lang = lang
    this.retrofit4weather = createRetrofit4WeatherInstance(this.proxy)

    return this
  }

  /**
   * Set proxy for getting data from OpenWeatherMap.org
   *
   * @param proxy Proxy
   */
  fun setProxy(proxy: Proxy): OWM {
    this.proxy = proxy

    this.retrofit4weather = createRetrofit4WeatherInstance(this.proxy)
    this.retrofit4pollution = createRetrofit4PollutionInstance(this.proxy)
    this.retrofit4other = createRetrofit4OtherInstance(this.proxy)

    return this
  }

  /**
   * Set authenticated proxy for getting data from OpenWeatherMap.org
   *
   * @param proxy Proxy
   * @param user User name for the proxy
   * @param pass Password for the proxy
   */
  fun setProxy(proxy: Proxy, user: String, pass: String): OWM {
    setProxy(proxy)
    SystemTools.setProxyAuthDetails(user, pass)

    return this
  }

  /**
   * Set HTTP proxy for getting data from OpenWeatherMap.org
   *
   * @param host Host address of the proxy
   * @param port Port address of the proxy
   */
  fun setProxy(host: String, port: Int): OWM {
    setProxy(host, port, Proxy.Type.HTTP)

    return this
  }

  /**
   * Set proxy of any type for getting data from OpenWeatherMap.org
   *
   * @param host Host address of the proxy
   * @param port Port address of the proxy
   * @param type Type of the proxy
   */
  fun setProxy(host: String, port: Int, type: Proxy.Type): OWM {
    proxy = Proxy(type, InetSocketAddress(host, port))
    setProxy(proxy)

    return this
  }

  /**
   * Set authenticated HTTP proxy for getting data from OpenWeatherMap.org
   *
   * @param host Host address of the proxy
   * @param port Port address of the proxy
   * @param user User name for the proxy if required
   * @param pass Password for the proxy if required
   */
  fun setProxy(host: String, port: Int, user: String, pass: String): OWM {
    setProxy(host, port, user, pass, Proxy.Type.HTTP)

    return this
  }

  /**
   * Set authenticated proxy of any type for getting data from OpenWeatherMap.org
   *
   * @param host Host address of the proxy
   * @param port Port address of the proxy
   * @param user User name for the proxy if required
   * @param pass Password for the proxy if required
   * @param type Type of the proxy
   */
  fun setProxy(host: String, port: Int, user: String, pass: String, type: Proxy.Type): OWM {
    setProxy(host, port, type)
    SystemTools.setProxyAuthDetails(user, pass)

    return this
  }

  /**
   * Remove proxy (i.e., direct connection) for getting data from OpenWeatherMap.org
   */
  fun setNoProxy(): OWM {
    setProxy(Proxy.NO_PROXY)

    return this
  }

  /**
   * Reset proxy to system's proxy for getting data from OpenWeatherMap.org
   */
  fun resetProxy() {
    setProxy(SystemTools.getSystemProxy())
    SystemTools.setProxyAuthDetails("", "")
  }

  @Throws(APIException::class)
  fun currentWeatherByCityName(cityName: String): CurrentWeather {
    val api = retrofit4weather.create(CurrentWeatherAPI::class.java)

    val apiCall = api.getCurrentWeatherByCityName(cityName)
    println("apiCall = ${apiCall.request()}")
    val apiResp = apiCall.execute()
    var weather = apiResp.body()

    if (weather == null) {
      // TODO: if got weather, then check its respCode and throw if it is an error
      // Also, setup a conf. for toggling this - if someone wish to handle himself
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      weather = CurrentWeather()
    }

    return weather
  }

  @Throws(APIException::class)
  fun currentWeatherByCityName(cityName: String, countryCode: OWM.Country): CurrentWeather {
    return currentWeatherByCityName(cityName + "," + countryCode)
  }

  @Throws(APIException::class)
  fun currentWeatherByCityId(cityId: Int): CurrentWeather {
    val api = retrofit4weather.create(CurrentWeatherAPI::class.java)

    val apiCall = api.getCurrentWeatherByCityId(cityId)
    val apiResp = apiCall.execute()
    var weather = apiResp.body()

    if (weather == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      weather = CurrentWeather()
    }

    return weather
  }

  @Throws(APIException::class)
  fun currentWeatherByCoords(latitude: Double, longitude: Double): CurrentWeather {
    val api = retrofit4weather.create(CurrentWeatherAPI::class.java)

    val apiCall = api.getCurrentWeatherByCoords(latitude, longitude)
    val apiResp = apiCall.execute()
    var weather = apiResp.body()

    if (weather == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      weather = CurrentWeather()
    }

    return weather
  }

  @Throws(APIException::class)
  fun currentWeatherByZipCode(zipCode: Int): CurrentWeather {
    return currentWeatherByZipCode(zipCode, OWM.Country.UNITED_STATES)
  }

  @Throws(APIException::class)
  fun currentWeatherByZipCode(zipCode: Int, countryCode: OWM.Country): CurrentWeather {
    val api = retrofit4weather.create(CurrentWeatherAPI::class.java)

    val apiCall = api.getCurrentWeatherByZipCode(zipCode.toString() + "," + countryCode)
    val apiResp = apiCall.execute()
    var weather = apiResp.body()

    if (weather == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      weather = CurrentWeather()
    }

    return weather
  }

  @Throws(APIException::class)
  fun hourlyWeatherForecastByCityName(cityName: String): HourlyWeatherForecast {
    val api = retrofit4weather.create(HourlyWeatherForecastAPI::class.java)

    val apiCall = api.getHourlyWeatherForecastByCityName(cityName)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = HourlyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun hourlyWeatherForecastByCityName(cityName: String, countryCode: OWM.Country): HourlyWeatherForecast {
    return hourlyWeatherForecastByCityName(cityName + "," + countryCode)
  }

  @Throws(APIException::class)
  fun hourlyWeatherForecastByCityId(cityId: Int): HourlyWeatherForecast {
    val api = retrofit4weather.create(HourlyWeatherForecastAPI::class.java)

    val apiCall = api.getHourlyWeatherForecastByCityId(cityId)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = HourlyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun hourlyWeatherForecastByCoords(latitude: Double, longitude: Double): HourlyWeatherForecast {
    val api = retrofit4weather.create(HourlyWeatherForecastAPI::class.java)

    val apiCall = api.getHourlyWeatherForecastByCoords(latitude, longitude)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = HourlyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun hourlyWeatherForecastByZipCode(zipCode: Int): HourlyWeatherForecast {
    return hourlyWeatherForecastByZipCode(zipCode, OWM.Country.UNITED_STATES)
  }

  @Throws(APIException::class)
  fun hourlyWeatherForecastByZipCode(zipCode: Int, countryCode: OWM.Country): HourlyWeatherForecast {
    val api = retrofit4weather.create(HourlyWeatherForecastAPI::class.java)

    val apiCall = api.getHourlyWeatherForecastByZipCode(zipCode.toString() + "," + countryCode)
    val apiResp = apiCall.execute()
    var forecast = apiResp.body()

    if (forecast == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      forecast = HourlyWeatherForecast()
    }

    return forecast
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityName: String): DailyWeatherForecast {
    return dailyWeatherForecastByCityName(cityName, OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
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
    return dailyWeatherForecastByCityName(cityName, countryCode, OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityName(cityName: String, countryCode: OWM.Country, count: Int): DailyWeatherForecast {
    return dailyWeatherForecastByCityName(cityName + "," + countryCode, count)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByCityId(cityId: Int): DailyWeatherForecast {
    return dailyWeatherForecastByCityId(cityId, OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
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
    return dailyWeatherForecastByCoords(latitude, longitude, OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
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
    return dailyWeatherForecastByZipCode(zipCode, OWM.Country.UNITED_STATES, OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int, count: Int): DailyWeatherForecast {
    return dailyWeatherForecastByZipCode(zipCode, OWM.Country.UNITED_STATES, count)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int, countryCode: OWM.Country): DailyWeatherForecast {
    return dailyWeatherForecastByZipCode(zipCode, countryCode, OWM_DATA_V25_DAILY_WEATHER_FORECAST_MAX_COUNT)
  }

  @Throws(APIException::class)
  fun dailyWeatherForecastByZipCode(zipCode: Int, countryCode: OWM.Country, count: Int): DailyWeatherForecast {
    val api = retrofit4weather.create(DailyWeatherForecastAPI::class.java)

    val apiCall = api.getDailyWeatherForecastByZipCode(zipCode.toString() + "," + countryCode, count)
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
  fun currentUVIndexByCoords(latitude: Double, longitude: Double): CurrentUVIndex {
    val api = retrofit4other.create(CurrentUVIndexAPI::class.java)

    val apiCall = api.getCurrentUVIndexByCoords(latitude, longitude)
    val apiResp = apiCall.execute()
    var uvIndex = apiResp.body()

    if (uvIndex == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      uvIndex = CurrentUVIndex()
    }

    return uvIndex
  }

  @Throws(APIException::class)
  fun dailyUVIndexForecastByCoords(latitude: Double, longitude: Double): List<DailyUVIndexForecast> {
    val api = retrofit4other.create(DailyUVIndexForecastAPI::class.java)

    val apiCall = api.getDailyUVIndexForecastByCoords(latitude, longitude)
    val apiResp = apiCall.execute()
    var uvIndexList = apiResp.body()

    if (uvIndexList == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      uvIndexList = listOf(DailyUVIndexForecast())
    }

    return uvIndexList
  }

  @Throws(APIException::class)
  fun historicalUVIndexByCoords(latitude: Double, longitude: Double, count: Int, startTime: Long, endTime: Long): List<HistoricalUVIndex> {
    val api = retrofit4other.create(HistoricalUVIndexAPI::class.java)

    val apiCall = api.getHistoricalUVIndexByCoords(latitude, longitude, count, startTime, endTime)
    val apiResp = apiCall.execute()
    var uvIndexList = apiResp.body()

    if (uvIndexList == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      uvIndexList = listOf(HistoricalUVIndex())
    }

    return uvIndexList
  }

  @Throws(APIException::class)
  fun historicalUVIndexByCoords(latitude: Double, longitude: Double, count: Int, startTime: Date, endTime: Date): List<HistoricalUVIndex> {
    return historicalUVIndexByCoords(latitude, longitude, count, startTime.time / 1000, endTime.time / 1000)
  }

  @Throws(APIException::class)
  fun historicalUVIndexByCoords(latitude: Double, longitude: Double, startTime: Long, endTime: Long): List<HistoricalUVIndex> {
    return historicalUVIndexByCoords(latitude, longitude, OWM_DATA_V25_HISTORICAL_UV_INDEX_MAX_COUNT, startTime, endTime)
  }

  @Throws(APIException::class)
  fun historicalUVIndexByCoords(latitude: Double, longitude: Double, startTime: Date, endTime: Date): List<HistoricalUVIndex> {
    return historicalUVIndexByCoords(latitude, longitude, OWM_DATA_V25_HISTORICAL_UV_INDEX_MAX_COUNT, startTime.time / 1000, endTime.time / 1000)
  }

  @Throws(APIException::class)
  fun airPollutionByCoords(latitude: Double, longitude: Double, dateTime: String): AirPollution {
    val api = retrofit4pollution.create(AirPollutionAPI::class.java)

    val apiCall = api.getAirPollutionByCoords(latitude, longitude, dateTime)
    val apiResp = apiCall.execute()
    var pollution = apiResp.body()

    if (pollution == null) {
      if (!apiResp.isSuccessful) {
        throw APIException(apiResp.code(), apiResp.message())
      }

      pollution = AirPollution()
    }

    return pollution
  }

  @Throws(APIException::class)
  fun airPollutionByCoords(latitude: Double, longitude: Double, dateTime: Date): AirPollution {
    return airPollutionByCoords(latitude, longitude, dateTime.toInstant().toString())
  }

  @Throws(APIException::class)
  fun currentAirPollutionByCoords(latitude: Double, longitude: Double): AirPollution {
    return airPollutionByCoords(latitude, longitude, "current")
  }

  /**
   * Init Retrofit for getting weather data from OpenWeatherMap.org
   *
   * @param proxy Proxy
   */
  private fun createRetrofit4WeatherInstance(proxy: Proxy): Retrofit {
    val clientBuilder = OkHttpClient.Builder().proxy(proxy)

    OkHttpTools.addQueryParameter(clientBuilder, "appid", apiKey)
    OkHttpTools.addQueryParameter(clientBuilder, "type", accuracy.value)
    OkHttpTools.addQueryParameter(clientBuilder, "lang", lang.value)

    if (unit != OWM.Unit.STANDARD) {
      OkHttpTools.addQueryParameter(clientBuilder, "units", unit.value)
    }

    val client = clientBuilder.build()
    val gson = GsonBuilder().setLenient().create()

    val builder = Retrofit.Builder()
      .client(client)
      .baseUrl(OWM_DATA_V25_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))

    return builder.build()
  }

  /**
   * Init Retrofit for getting pollution data from OpenWeatherMap.org
   *
   * @param proxy Proxy
   */
  private fun createRetrofit4PollutionInstance(proxy: Proxy): Retrofit {
    val clientBuilder = OkHttpClient.Builder().proxy(proxy)

    OkHttpTools.addQueryParameter(clientBuilder, "appid", apiKey)

    val client = clientBuilder.build()
    val gson = GsonBuilder().setLenient().create()

    val builder = Retrofit.Builder()
      .client(client)
      .baseUrl(OWM_POLLUTION_V10_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))

    return builder.build()
  }

  /**
   * Init Retrofit for getting other data from OpenWeatherMap.org
   *
   * @param proxy Proxy
   */
  private fun createRetrofit4OtherInstance(proxy: Proxy): Retrofit {
    val clientBuilder = OkHttpClient.Builder().proxy(proxy)

    OkHttpTools.addQueryParameter(clientBuilder, "appid", apiKey)

    val client = clientBuilder.build()
    val gson = GsonBuilder().setLenient().create()

    val builder = Retrofit.Builder()
      .client(client)
      .baseUrl(OWM_DATA_V25_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))

    return builder.build()
  }

  /**
   * Search accuracy that can be set for getting data from OpenWeatherMap.org
   *
   * [OpenWeatherMap.org's Search accuracy][https://openweathermap.org/current#accuracy]
   */
  enum class Accuracy(val value: String) {
    ACCURATE("accurate"),
    LIKE("like")
  }

  /**
   * Unit that can be set for getting data from OpenWeatherMap.org
   *
   * [OpenWeatherMap.org's Units format][https://openweathermap.org/current#data]
   */
  enum class Unit(val value: String) {
    IMPERIAL("imperial"),
    METRIC("metric"),
    STANDARD("standard")
  }

  /**
   * Language that can be set for getting data from OpenWeatherMap.org
   *
   * [OpenWeatherMap.org's Multilingual support][https://openweathermap.org/current#multi]
   */
  enum class Language(val value: String) {
    ARABIC("ar"),
    BULGARIAN("bg"),
    CATALAN("ca"),
    CHINESE_SIMPLIFIED("zh_cn"),
    CHINESE_TRADITIONAL("zh_tw"),
    CROATIAN("hr"),
    CZECH("cz"),
    DUTCH("nl"),
    ENGLISH("en"),
    FINNISH("fi"),
    FRENCH("fr"),
    GALICIAN("gl"),
    GREEK("el"),
    GERMAN("de"),
    HUNGARIAN("hu"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KOREAN("kr"),
    LATVIAN("la"),
    LITHUANIAN("lt"),
    MACEDONIAN("mk"),
    PERSIAN("fa"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SPANISH("es"),
    SWEDISH("se"),
    TURKISH("tr"),
    UKRAINIAN("ua"),
    VIETNAMESE("vi")
  }

  /**
   * Country that can be set for getting data from OpenWeatherMap.org
   */
  enum class Country(val value: String) {
    AFGHANISTAN("AF"),
    ALAND_ISLANDS("AX"),
    ALBANIA("AL"),
    ALGERIA("DZ"),
    AMERICAN_SAMOA("AS"),
    ANDORRA("AD"),
    ANGOLA("AO"),
    ANGUILLA("AI"),
    ANTARCTICA("AQ"),
    ANTIGUA_AND_BARBUDA("AG"),
    ARGENTINA("AR"),
    ARMENIA("AM"),
    ARUBA("AW"),
    AUSTRALIA("AU"),
    AUSTRIA("AT"),
    AZERBAIJAN("AZ"),
    BAHAMAS("BS"),
    BAHRAIN("BH"),
    BANGLADESH("BD"),
    BARBADOS("BB"),
    BELARUS("BY"),
    BELGIUM("BE"),
    BELIZE("BZ"),
    BENIN("BJ"),
    BERMUDA("BM"),
    BHUTAN("BT"),
    BOLIVIA("BO"),
    BOSNIA_AND_HERZEGOVINA("BA"),
    BOTSWANA("BW"),
    BOUVET_ISLAND("BV"),
    BRAZIL("BR"),
    BRITISH_INDIAN_OCEAN_TERRITORY("IO"),
    BRITISH_VIRGIN_ISLANDS("VG"),
    BRUNEI("BN"),
    BULGARIA("BG"),
    BURKINA_FASO("BF"),
    BURUNDI("BI"),
    CAMBODIA("KH"),
    CAMEROON("CM"),
    CANADA("CA"),
    CAPE_VERDE("CV"),
    CARIBBEAN_NETHERLANDS("BQ"),
    CAYMAN_ISLANDS("KY"),
    CENTRAL_AFRICAN_REPUBLIC("CF"),
    CHAD("TD"),
    CHILE("CL"),
    CHINA("CN"),
    CHRISTMAS_ISLAND("CX"),
    COCOS_KEELING_ISLANDS("CC"),
    COLOMBIA("CO"),
    COMOROS("KM"),
    CONGO_BRAZZAVILLE("CG"),
    CONGO_KINSHASA("CD"),
    COOK_ISLANDS("CK"),
    COSTA_RICA("CR"),
    CROATIA("HR"),
    CUBA("CU"),
    CURACAO("CW"),
    CYPRUS("CY"),
    CZECH_REPUBLIC("CZ"),
    COTE_D_IVOIRE("CI"),
    DENMARK("DK"),
    DJIBOUTI("DJ"),
    DOMINICA("DM"),
    DOMINICAN_REPUBLIC("DO"),
    ECUADOR("EC"),
    EGYPT("EG"),
    EL_SALVADOR("SV"),
    EQUATORIAL_GUINEA("GQ"),
    ERITREA("ER"),
    ESTONIA("EE"),
    ETHIOPIA("ET"),
    FALKLAND_ISLANDS("FK"),
    FAROE_ISLANDS("FO"),
    FIJI("FJ"),
    FINLAND("FI"),
    FRANCE("FR"),
    FRENCH_GUIANA("GF"),
    FRENCH_POLYNESIA("PF"),
    FRENCH_SOUTHERN_TERRITORIES("TF"),
    GABON("GA"),
    GAMBIA("GM"),
    GEORGIA("GE"),
    GERMANY("DE"),
    GHANA("GH"),
    GIBRALTAR("GI"),
    GREECE("GR"),
    GREENLAND("GL"),
    GRENADA("GD"),
    GUADELOUPE("GP"),
    GUAM("GU"),
    GUATEMALA("GT"),
    GUERNSEY("GG"),
    GUINEA("GN"),
    GUINEA_BISSAU("GW"),
    GUYANA("GY"),
    HAITI("HT"),
    HEARD_AND_MCDONALD_ISLANDS("HM"),
    HONDURAS("HN"),
    HONG_KONG_SAR_CHINA("HK"),
    HUNGARY("HU"),
    ICELAND("IS"),
    INDIA("IN"),
    INDONESIA("ID"),
    IRAN("IR"),
    IRAQ("IQ"),
    IRELAND("IE"),
    ISLE_OF_MAN("IM"),
    ISRAEL("IL"),
    ITALY("IT"),
    JAMAICA("JM"),
    JAPAN("JP"),
    JERSEY("JE"),
    JORDAN("JO"),
    KAZAKHSTAN("KZ"),
    KENYA("KE"),
    KIRIBATI("KI"),
    KUWAIT("KW"),
    KYRGYZSTAN("KG"),
    LAOS("LA"),
    LATVIA("LV"),
    LEBANON("LB"),
    LESOTHO("LS"),
    LIBERIA("LR"),
    LIBYA("LY"),
    LIECHTENSTEIN("LI"),
    LITHUANIA("LT"),
    LUXEMBOURG("LU"),
    MACAU_SAR_CHINA("MO"),
    MACEDONIA("MK"),
    MADAGASCAR("MG"),
    MALAWI("MW"),
    MALAYSIA("MY"),
    MALDIVES("MV"),
    MALI("ML"),
    MALTA("MT"),
    MARSHALL_ISLANDS("MH"),
    MARTINIQUE("MQ"),
    MAURITANIA("MR"),
    MAURITIUS("MU"),
    MAYOTTE("YT"),
    MEXICO("MX"),
    MICRONESIA("FM"),
    MOLDOVA("MD"),
    MONACO("MC"),
    MONGOLIA("MN"),
    MONTENEGRO("ME"),
    MONTSERRAT("MS"),
    MOROCCO("MA"),
    MOZAMBIQUE("MZ"),
    MYANMAR_BURMA("MM"),
    NAMIBIA("NA"),
    NAURU("NR"),
    NEPAL("NP"),
    NETHERLANDS("NL"),
    NEW_CALEDONIA("NC"),
    NEW_ZEALAND("NZ"),
    NICARAGUA("NI"),
    NIGER("NE"),
    NIGERIA("NG"),
    NIUE("NU"),
    NORFOLK_ISLAND("NF"),
    NORTH_KOREA("KP"),
    NORTHERN_MARIANA_ISLANDS("MP"),
    NORWAY("NO"),
    OMAN("OM"),
    PAKISTAN("PK"),
    PALAU("PW"),
    PALESTINIAN_TERRITORIES("PS"),
    PANAMA("PA"),
    PAPUA_NEW_GUINEA("PG"),
    PARAGUAY("PY"),
    PERU("PE"),
    PHILIPPINES("PH"),
    PITCAIRN_ISLANDS("PN"),
    POLAND("PL"),
    PORTUGAL("PT"),
    PUERTO_RICO("PR"),
    QATAR("QA"),
    ROMANIA("RO"),
    RUSSIA("RU"),
    RWANDA("RW"),
    REUNION("RE"),
    SAMOA("WS"),
    SAN_MARINO("SM"),
    SAUDI_ARABIA("SA"),
    SENEGAL("SN"),
    SERBIA("RS"),
    SEYCHELLES("SC"),
    SIERRA_LEONE("SL"),
    SINGAPORE("SG"),
    SINT_MAARTEN("SX"),
    SLOVAKIA("SK"),
    SLOVENIA("SI"),
    SOLOMON_ISLANDS("SB"),
    SOMALIA("SO"),
    SOUTH_AFRICA("ZA"),
    SOUTH_GEORGIA_AND_SOUTH_SANDWICH_ISLANDS("GS"),
    SOUTH_KOREA("KR"),
    SOUTH_SUDAN("SS"),
    SPAIN("ES"),
    SRI_LANKA("LK"),
    ST_BARTHELEMY("BL"),
    ST_HELENA("SH"),
    ST_KITTS_AND_NEVIS("KN"),
    ST_LUCIA("LC"),
    ST_MARTIN("MF"),
    ST_PIERRE_AND_MIQUELON("PM"),
    ST_VINCENT_AND_GRENADINES("VC"),
    SUDAN("SD"),
    SURINAME("SR"),
    SVALBARD_AND_JAN_MAYEN("SJ"),
    SWAZILAND("SZ"),
    SWEDEN("SE"),
    SWITZERLAND("CH"),
    SYRIA("SY"),
    SAO_TOME_AND_PRINCIPE("ST"),
    TAIWAN("TW"),
    TAJIKISTAN("TJ"),
    TANZANIA("TZ"),
    THAILAND("TH"),
    TIMOR_LESTE("TL"),
    TOGO("TG"),
    TOKELAU("TK"),
    TONGA("TO"),
    TRINIDAD_AND_TOBAGO("TT"),
    TUNISIA("TN"),
    TURKEY("TR"),
    TURKMENISTAN("TM"),
    TURKS_AND_CAICOS_ISLANDS("TC"),
    TUVALU("TV"),
    US_OUTLYING_ISLANDS("UM"),
    US_VIRGIN_ISLANDS("VI"),
    UGANDA("UG"),
    UKRAINE("UA"),
    UNITED_ARAB_EMIRATES("AE"),
    UNITED_KINGDOM("GB"),
    UNITED_STATES("US"),
    URUGUAY("UY"),
    UZBEKISTAN("UZ"),
    VANUATU("VU"),
    VATICAN_CITY("VA"),
    VENEZUELA("VE"),
    VIETNAM("VN"),
    WALLIS_AND_FUTUNA("WF"),
    WESTERN_SAHARA("EH"),
    YEMEN("YE"),
    ZAMBIA("ZM"),
    ZIMBABWE("ZW")
  }
}
