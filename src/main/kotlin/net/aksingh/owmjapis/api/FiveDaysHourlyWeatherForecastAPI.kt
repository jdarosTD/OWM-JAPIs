package net.aksingh.owmjapis.api

import net.aksingh.owmjapis.model.HourlyWeatherForecast

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Jonathan DA ROS on 18/04/2019.
 */
interface FiveDaysHourlyWeatherForecastAPI {

  @GET("forecast")
  fun getHourlyWeatherForecastByCityName(
    @Query("q") name: String
  ): Call<HourlyWeatherForecast>

  @GET("forecast")
  fun getHourlyWeatherForecastByCityId(
    @Query("id") id: Int
  ): Call<HourlyWeatherForecast>

  @GET("forecast")
  fun getHourlyWeatherForecastByCoords(
    @Query("lat") lat: Double,
    @Query("lon") lon: Double
  ): Call<HourlyWeatherForecast>

  @GET("forecast")
  fun getHourlyWeatherForecastByZipCode(
    @Query("zip") zip: String
  ): Call<HourlyWeatherForecast>
}
