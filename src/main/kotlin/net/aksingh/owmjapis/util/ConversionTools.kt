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

package net.aksingh.owmjapis.util

/**
 * Provides methods for conversions, like converting
 * windData direction from degree to direction code or name.
 *
 * @author Ashutosh Kumar Singh
 * @version 2017-11-06
 * @since 2.5.1.0
 */
class ConversionTools {

  companion object Static {

    /**
     * Converts degree to direction code.
     *
     * @param degree Degree of windData (as received from OpenWeatherMap.org).
     * @return Direction code, e.g., "N", "NE", etc.
     * @throws IllegalArgumentException Degree should be between 0 and 360.
     */
    @JvmStatic
    @Throws(IllegalArgumentException::class)
    fun convertDegree2DirectionCode(degree: Double): String {
      val directionCode: String

      // degree should be between 0 and 360
      if (degree < 0.0f || degree > 360.0f) {
        throw IllegalArgumentException("Degree cannot be less than 0 or more than 360.")
      }

      when {
        degree <= 11.25f -> directionCode = "N"
        degree <= 33.75f -> directionCode = "NNE"
        degree <= 56.25f -> directionCode = "NE"
        degree <= 78.75f -> directionCode = "ENE"
        degree <= 101.25f -> directionCode = "E"
        degree <= 123.75f -> directionCode = "ESE"
        degree <= 146.25f -> directionCode = "SE"
        degree <= 168.75f -> directionCode = "SSE"
        degree <= 191.25f -> directionCode = "S"
        degree <= 213.75f -> directionCode = "SSW"
        degree <= 236.25f -> directionCode = "SW"
        degree <= 258.75f -> directionCode = "WSW"
        degree <= 281.25f -> directionCode = "W"
        degree <= 303.75f -> directionCode = "WNW"
        degree <= 326.25f -> directionCode = "NW"
        degree <= 348.75f -> directionCode = "NNW"
        else -> directionCode = "N"
      }

      return directionCode
    }

    /**
     * Converts degree to direction name.
     *
     * @param degree Degree of windData (as received from OpenWeatherMap.org).
     * @return Direction name, e.g., "North", "North East", etc.
     * @throws IllegalArgumentException Degree should be between 0 and 360.
     */
    @JvmStatic
    @Throws(IllegalArgumentException::class)
    fun convertDegree2DirectionName(degree: Double): String {
      val directionName: String
      val directionCode = convertDegree2DirectionCode(degree)

      when (directionCode) {
        "N" -> directionName = "North"
        "NNE" -> directionName = "North North East"
        "NE" -> directionName = "North East"
        "ENE" -> directionName = "East North East"
        "E" -> directionName = "East"
        "ESE" -> directionName = "East South East"
        "SE" -> directionName = "South East"
        "SSE" -> directionName = "South South East"
        "S" -> directionName = "South"
        "SSW" -> directionName = "South South West"
        "SW" -> directionName = "South West"
        "WSW" -> directionName = "West South West"
        "W" -> directionName = "West"
        "WNW" -> directionName = "West North West"
        "NW" -> directionName = "North West"
        "NNW" -> directionName = "North North West"
        else -> directionName = "North"
      }

      return directionName
    }
  }
}
