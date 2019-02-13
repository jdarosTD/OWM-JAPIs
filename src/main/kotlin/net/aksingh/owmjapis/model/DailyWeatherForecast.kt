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
import net.aksingh.owmjapis.model.param.City
import net.aksingh.owmjapis.model.param.ForecastData

data class DailyWeatherForecast(
  @field:SerializedName("cod")
  val respCode: String? = null,

  @field:SerializedName("message")
  val message: Double? = null,

  @field:SerializedName("city")
  val cityData: City? = null,

  @field:SerializedName("cnt")
  val dataCount: Int? = null,

  @field:SerializedName("list")
  val dataList: List<ForecastData?>? = null
) {

  fun hasRespCode(): Boolean = respCode != null

  fun hasMessage(): Boolean = message != null

  fun hasCityData(): Boolean = cityData != null

  fun hasDataCount(): Boolean = dataCount != null

  fun hasDataList(): Boolean = dataList != null

  companion object Static {
    @JvmStatic
    fun fromJson(json: String): DailyWeatherForecast {
      return GsonBuilder().create().fromJson(json, DailyWeatherForecast::class.java)
    }

    @JvmStatic
    fun toJson(pojo: DailyWeatherForecast): String {
      return GsonBuilder().create().toJson(pojo)
    }

    @JvmStatic
    fun toJsonPretty(pojo: DailyWeatherForecast): String {
      return GsonBuilder().setPrettyPrinting().create().toJson(pojo)
    }
  }
}
