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

data class ForecastData(
  @field:SerializedName("dt")
  private val dt: Int? = null,

  @field:SerializedName("temp")
  val tempData: Temp? = null,

  @field:SerializedName("pressure")
  val pressure: Double? = null,

  @field:SerializedName("humidity")
  val humidity: Double? = null,

  @field:SerializedName("weather")
  val weatherList: List<Weather?>? = null,

  @field:SerializedName("speed")
  val speed: Double? = null,

  @field:SerializedName("deg")
  val degree: Double? = null,

  @field:SerializedName("clouds")
  val cloud: Double? = null,

  @field:SerializedName("rain")
  val rain: Double? = null,

  @field:SerializedName("snow")
  val snow: Double? = null
) {

  var dateTime: Date? = null
    get() {
      if (dt != null) {
        return Date(dt.toLong() * 1000L)
      }
      return null
    }

  fun hasDateTime(): Boolean = dateTime != null

  fun hasTempData(): Boolean = tempData != null

  fun hasPressure(): Boolean = pressure != null

  fun hasHumidity(): Boolean = humidity != null

  fun hasWeatherList(): Boolean = weatherList != null

  fun hasSpeed(): Boolean = speed != null

  fun hasDegree(): Boolean = degree != null

  fun hasCloud(): Boolean = cloud != null

  fun hasRain(): Boolean = rain != null

  fun hasSnow(): Boolean = snow != null

  companion object Static {
    @JvmStatic
    fun fromJson(json: String): ForecastData {
      return GsonBuilder().create().fromJson(json, ForecastData::class.java)
    }

    @JvmStatic
    fun toJson(pojo: ForecastData): String {
      return GsonBuilder().create().toJson(pojo)
    }

    @JvmStatic
    fun toJsonPretty(pojo: ForecastData): String {
      return GsonBuilder().setPrettyPrinting().create().toJson(pojo)
    }
  }
}
