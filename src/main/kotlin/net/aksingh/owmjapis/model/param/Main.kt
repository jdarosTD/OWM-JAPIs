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

data class Main(
  @field:SerializedName("temp")
  val temp: Double? = null,

  @field:SerializedName("temp_min")
  val tempMin: Double? = null,

  @field:SerializedName("temp_max")
  val tempMax: Double? = null,

  @field:SerializedName("pressure")
  val pressure: Double? = null,

  @field:SerializedName("sea_level")
  val seaLevel: Double? = null,

  @field:SerializedName("grnd_level")
  val groundLevel: Double? = null,

  @field:SerializedName("humidity")
  val humidity: Double? = null,

  @field:SerializedName("temp_kf")
  val tempKf: Double? = null
) {

  fun hasTemp(): Boolean = temp != null

  fun hasTempMax(): Boolean = tempMax != null

  fun hasTempMin(): Boolean = tempMin != null

  fun hasPressure(): Boolean = pressure != null

  fun hasSeaLevel(): Boolean = seaLevel != null

  fun hasGroundLevel(): Boolean = groundLevel != null

  fun hasHumidity(): Boolean = humidity != null

  fun hasTempKf(): Boolean = tempKf != null

  companion object Static {
    @JvmStatic
    fun fromJson(json: String): Main {
      return GsonBuilder().create().fromJson(json, Main::class.java)
    }

    @JvmStatic
    fun toJson(pojo: Main): String {
      return GsonBuilder().create().toJson(pojo)
    }

    @JvmStatic
    fun toJsonPretty(pojo: Main): String {
      return GsonBuilder().setPrettyPrinting().create().toJson(pojo)
    }
  }
}
