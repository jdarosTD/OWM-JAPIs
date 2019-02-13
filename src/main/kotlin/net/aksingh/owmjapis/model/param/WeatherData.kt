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

package net.aksingh.owmjapis.model.param

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherData(
  @field:SerializedName("dt")
  private val dt: Int? = null,

  @field:SerializedName("main")
  val mainData: Main? = null,

  @field:SerializedName("temp")
  val tempData: Temp? = null,

  @field:SerializedName("pressure")
  val pressure: Double? = null,

  @field:SerializedName("humidity")
  val humidity: Double? = null,

  @field:SerializedName("weather")
  val weatherList: List<Weather?>? = null,

  @field:SerializedName("clouds")
  val cloudData: Cloud? = null,

  @field:SerializedName("wind")
  val windData: Wind? = null,

  @field:SerializedName("sys")
  val systemData: System? = null,

  @field:SerializedName("dt_txt")
  val dateTimeText: String? = null
) {

  var dateTime: Date? = null
    get() {
      if (dt != null) {
        return Date(dt.toLong() * 1000L)
      }
      return null
    }

  fun hasDateTime(): Boolean = dateTime != null

  fun hasMainData(): Boolean = mainData != null

  fun hasTempData(): Boolean = tempData != null

  fun hasPressure(): Boolean = pressure != null

  fun hasHumidity(): Boolean = humidity != null

  fun hasWeatherList(): Boolean = weatherList != null

  fun hasCloudData(): Boolean = cloudData != null

  fun hasWindData(): Boolean = windData != null

  fun hassystemData(): Boolean = systemData != null

  fun hasDateTimeText(): Boolean = dateTimeText != null

  companion object Static {
    @JvmStatic
    fun fromJson(json: String): WeatherData {
      return GsonBuilder().create().fromJson(json, WeatherData::class.java)
    }

    @JvmStatic
    fun toJson(pojo: WeatherData): String {
      return GsonBuilder().create().toJson(pojo)
    }

    @JvmStatic
    fun toJsonPretty(pojo: WeatherData): String {
      return GsonBuilder().setPrettyPrinting().create().toJson(pojo)
    }
  }
}
