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

import okhttp3.OkHttpClient

/**
 * Provides methods for OkHttp lib. handling.
 *
 * @author Ashutosh Kumar Singh
 * @version 2017-11-08
 * @since 2.5.1.0
 */
internal class OkHttpTools {

  companion object Static {

    /**
     * Add a query parameter to OkHttpClient.Builder instance.
     * It is used to add default parameters to Retrofit instance.
     *
     * @param httpClientBuilder OkHttpClient.Builder instance
     * @param key Key in the query string
     * @param value Value in the query string
     */
    @JvmStatic
    fun addQueryParameter(httpClientBuilder: OkHttpClient.Builder, key: String, value: String) {
      httpClientBuilder.addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
          .addQueryParameter(key, value)
          .build()

        val requestBuilder = original.newBuilder()
          .url(url)

        val request = requestBuilder.build()

        chain.proceed(request)
      }
    }
  }
}
