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

data class Temp(
  @field:SerializedName("day")
  val tempDay: Double? = null,

  @field:SerializedName("min")
  val tempMin: Double? = null,

  @field:SerializedName("max")
  val tempMax: Double? = null,

  @field:SerializedName("night")
  val tempNight: Double? = null,

  @field:SerializedName("eve")
  val tempEvening: Double? = null,

  @field:SerializedName("morn")
  val tempMorning: Double? = null
) {

  fun hasTempDay(): Boolean = tempDay != null

  fun hasTempMin(): Boolean = tempMin != null

  fun hasTempMax(): Boolean = tempMax != null

  fun hasTempNight(): Boolean = tempNight != null

  fun hasTempEvening(): Boolean = tempEvening != null

  fun hasTempMorning(): Boolean = tempMorning != null

  companion object Static {
    @JvmStatic
    fun fromJson(json: String): Temp {
      return GsonBuilder().create().fromJson(json, Temp::class.java)
    }

    @JvmStatic
    fun toJson(pojo: Temp): String {
      return GsonBuilder().create().toJson(pojo)
    }

    @JvmStatic
    fun toJsonPretty(pojo: Temp): String {
      return GsonBuilder().setPrettyPrinting().create().toJson(pojo)
    }
  }
}
