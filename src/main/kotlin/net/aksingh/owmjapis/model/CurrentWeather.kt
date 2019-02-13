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

package net.aksingh.owmjapis.model

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import net.aksingh.owmjapis.model.param.*
import java.util.*

data class CurrentWeather(
  @field:SerializedName("dt")
  private val dt: Int? = null,

  @field:SerializedName("rain")
  val rainData: Rain? = null,

  @field:SerializedName("snow")
  val snowData: Snow? = null,

  @field:SerializedName("coord")
  val coordData: Coord? = null,

  @field:SerializedName("weather")
  val weatherList: List<Weather?>? = null,

  @field:SerializedName("name")
  val cityName: String? = null,

  @field:SerializedName("cod")
  val respCode: Int? = null,

  @field:SerializedName("main")
  val mainData: Main? = null,

  @field:SerializedName("clouds")
  val cloudData: Cloud? = null,

  @field:SerializedName("id")
  val cityId: Int? = null,

  @field:SerializedName("sys")
  val systemData: System? = null,

  @field:SerializedName("base")
  val baseStation: String? = null,

  @field:SerializedName("wind")
  val windData: Wind? = null
) {

  var dateTime: Date? = null
    get() {
      if (dt != null) {
        return Date(dt.toLong() * 1000L)
      }
      return null
    }

  fun hasDateTime(): Boolean = dateTime != null

  fun hasRainData(): Boolean = rainData != null

  fun hasSnowData(): Boolean = snowData != null

  fun hasCoordData(): Boolean = coordData != null

  fun hasWeatherList(): Boolean = weatherList != null

  fun hasCityName(): Boolean = cityName != null

  fun hasRespCode(): Boolean = respCode != null

  fun hasMainData(): Boolean = mainData != null

  fun hasCloudData(): Boolean = cloudData != null

  fun hasCityId(): Boolean = cityId != null

  fun hassystemData(): Boolean = systemData != null

  fun hasBaseStation(): Boolean = baseStation != null

  fun hasWindData(): Boolean = windData != null

  companion object Static {
    @JvmStatic
    fun fromJson(json: String): CurrentWeather {
      return GsonBuilder().create().fromJson(json, CurrentWeather::class.java)
    }

    @JvmStatic
    fun toJson(pojo: CurrentWeather): String {
      return GsonBuilder().create().toJson(pojo)
    }

    @JvmStatic
    fun toJsonPretty(pojo: CurrentWeather): String {
      return GsonBuilder().setPrettyPrinting().create().toJson(pojo)
    }
  }
}
