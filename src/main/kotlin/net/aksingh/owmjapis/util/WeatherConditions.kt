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
 * Provides methods for understanding weather conditions,
 * like retrieving weather description, icon's link, etc.
 *
 * @author Ashutosh Kumar Singh
 * @version 2017-12-06
 * @since 2.5.2.1
 */
class WeatherConditions {

  companion object Static {

    private val OWM_DATA_V25_ICON_LINK = "https://openweathermap.org/img/w/"

    /**
     * Tells description of weather condition.
     *
     * @param conditionId Id of weather condition.
     * @return Description of weather condition.
     */
    @JvmStatic
    fun getDescription(conditionId: Int): String {
      var description = "unknown weather condition"

      when (conditionId) {
        200 -> description = "thunderstorm with light rain"
        201 -> description = "thunderstorm with rain"
        202 -> description = "thunderstorm with heavy rain"
        210 -> description = "light thunderstorm"
        211 -> description = "thunderstorm"
        212 -> description = "heavy thunderstorm"
        221 -> description = "ragged thunderstorm"
        230 -> description = "thunderstorm with light drizzle"
        231 -> description = "thunderstorm with drizzle"
        232 -> description = "thunderstorm with heavy drizzle"
        300 -> description = "light intensity drizzle"
        301 -> description = "drizzle"
        302 -> description = "heavy intensity drizzle"
        310 -> description = "light intensity drizzle rain"
        311 -> description = "drizzle rain"
        312 -> description = "heavy intensity drizzle rain"
        313 -> description = "shower rain and drizzle"
        314 -> description = "heavy shower rain and drizzle"
        321 -> description = "shower drizzle"
        500 -> description = "light rain"
        501 -> description = "moderate rain"
        502 -> description = "heavy intensity rain"
        503 -> description = "very heavy rain"
        504 -> description = "extreme rain"
        511 -> description = "freezing rain"
        520 -> description = "light intensity shower rain"
        521 -> description = "shower rain"
        522 -> description = "heavy intensity shower rain"
        531 -> description = "ragged shower rain"
        600 -> description = "light snow"
        601 -> description = "snow"
        602 -> description = "heavy snow"
        611 -> description = "sleet"
        612 -> description = "shower sleet"
        615 -> description = "light rain and snow"
        616 -> description = "rain and snow"
        620 -> description = "light shower snow"
        621 -> description = "shower snow"
        622 -> description = "heavy shower snow"
        701 -> description = "mist"
        711 -> description = "smoke"
        721 -> description = "haze"
        731 -> description = "sand, dust whirls"
        741 -> description = "fog"
        751 -> description = "sand"
        761 -> description = "dust"
        762 -> description = "volcanic ash"
        771 -> description = "squalls"
        781 -> description = "tornado"
        800 -> description = "clear sky"
        801 -> description = "few clouds"
        802 -> description = "scattered clouds"
        803 -> description = "broken clouds"
        804 -> description = "overcast clouds"
        900 -> description = "tornado"
        901 -> description = "tropical storm"
        902 -> description = "hurricane"
        903 -> description = "cold"
        904 -> description = "hot"
        905 -> description = "windy"
        906 -> description = "hail"
        951 -> description = "calm"
        952 -> description = "light breeze"
        953 -> description = "gentle breeze"
        954 -> description = "moderate breeze"
        955 -> description = "fresh breeze"
        956 -> description = "strong breeze"
        957 -> description = "high wind, near gale"
        958 -> description = "gale"
        959 -> description = "severe gale"
        960 -> description = "storm"
        961 -> description = "violent storm"
        962 -> description = "hurricane"
      }

      return description
    }

    /**
     * Tells non-day/night icon code for a weather condition.
     *
     * @param conditionId Id of weather condition.
     * @return Icon code for a weather condition.
     */
    @JvmStatic
    fun getIconCode(conditionId: Int): Int {
      var iconCode = 0

      when (conditionId) {
        200 -> iconCode = 11
        201 -> iconCode = 11
        202 -> iconCode = 11
        210 -> iconCode = 11
        211 -> iconCode = 11
        212 -> iconCode = 11
        221 -> iconCode = 11
        230 -> iconCode = 11
        231 -> iconCode = 11
        232 -> iconCode = 11
        300 -> iconCode = 9
        301 -> iconCode = 9
        302 -> iconCode = 9
        310 -> iconCode = 9
        311 -> iconCode = 9
        312 -> iconCode = 9
        313 -> iconCode = 9
        314 -> iconCode = 9
        321 -> iconCode = 9
        500 -> iconCode = 10
        501 -> iconCode = 10
        502 -> iconCode = 10
        503 -> iconCode = 10
        504 -> iconCode = 10
        511 -> iconCode = 13
        520 -> iconCode = 9
        521 -> iconCode = 9
        522 -> iconCode = 9
        531 -> iconCode = 9
        600 -> iconCode = 13
        601 -> iconCode = 13
        602 -> iconCode = 13
        611 -> iconCode = 13
        612 -> iconCode = 13
        615 -> iconCode = 13
        616 -> iconCode = 13
        620 -> iconCode = 13
        621 -> iconCode = 13
        622 -> iconCode = 13
        701 -> iconCode = 50
        711 -> iconCode = 50
        721 -> iconCode = 50
        731 -> iconCode = 50
        741 -> iconCode = 50
        751 -> iconCode = 50
        761 -> iconCode = 50
        762 -> iconCode = 50
        771 -> iconCode = 50
        781 -> iconCode = 50
        800 -> iconCode = 1
        801 -> iconCode = 2
        802 -> iconCode = 3
        803 -> iconCode = 4
        804 -> iconCode = 4
      }

      return iconCode
    }

    /**
     * Tells day/night icon code for a weather condition.
     *
     * @param conditionId Id of weather condition.
     * @return Icon's link for a weather condition.
     */
    @JvmStatic
    fun getIconLink(iconName: String): String {
      return OWM_DATA_V25_ICON_LINK + iconName + ".png"
    }

    /**
     * Tells day/night icon link for a weather condition.
     *
     * @param iconCode Non-day/night icon code for a weather condition.
     * @param isDay Is it day or not, for which icon is needed?
     * @return Icon's link for a weather condition.
     */
    @JvmStatic
    fun getIconLink(iconCode: Int, isDay: Boolean): String {
      return if (isDay) {
        getIconDayLink(iconCode)
      } else {
        getIconNightLink(iconCode)
      }
    }

    /**
     * Tells day icon code for a weather condition.
     *
     * @param iconCode Non-day/night icon code for a weather condition.
     * @return Day icon code for a weather condition.
     */
    @JvmStatic
    fun getIconDayCode(iconCode: Int): String {
      return if (iconCode < 10) {
        "0" + iconCode.toString() + "d"
      } else {
        iconCode.toString() + "d"
      }
    }

    /**
     * Tells night icon code for a weather condition.
     *
     * @param iconCode Non-day/night icon code for a weather condition.
     * @return Night icon code for a weather condition.
     */
    @JvmStatic
    fun getIconNightCode(iconCode: Int): String {
      return if (iconCode < 10) {
        "0" + iconCode.toString() + "n"
      } else {
        iconCode.toString() + "n"
      }
    }

    /**
     * Tells day icon's link for a weather condition.
     *
     * @param iconCode Non-day/night icon code for a weather condition.
     * @return Day icon's link for a weather condition.
     */
    @JvmStatic
    fun getIconDayLink(iconCode: Int): String {
      return OWM_DATA_V25_ICON_LINK + getIconDayCode(iconCode) + ".png"
    }

    /**
     * Tells night icon's link for a weather condition.
     *
     * @param iconCode Non-day/night icon code for a weather condition.
     * @return Night icon's link for a weather condition.
     */
    @JvmStatic
    fun getIconNightLink(iconCode: Int): String {
      return OWM_DATA_V25_ICON_LINK + getIconNightCode(iconCode) + ".png"
    }
  }
}
