#OWM JAPIs

####Java Library for OpenWeatherMap.org Weather APIs

OWM JAPIs lets you develop weather-aware applications for **Android, Kotlin, and Java** platforms in minimum time. It is an easy-to-use, well-documented wrapper library for OpenWeatherMap.org's Weather APIs. You can easily **retrieve and use weather data** in your applications using this library.

OWM JAPIs allows you to **fetch weather data in only 3-5 lines of code**. You can develop applications and services for multiple platforms using this library, such as Windows, Mac OS X, Linux, and Android.

*Homepage:* [http://go.aksingh.net/owm-japis](http://go.aksingh.net/owm-japis)



###Why to use OWM JAPIs?
1. Free
2. Easy to use
3. Supports re-use
4. Minimizes the code
5. Developer-friendly

With OWM JAPIs, you can **focus just on your application's logic** and **delegate weather retrieval** to this library. As a bonus, weather retrieval code becomes very short using this library - as less as 3-5 lines of code can get you weather data from OpenWeatherMap.org in your Java, Android, or Kotlin application.

**Surprised? Have a look on the example(s) below.**



##How to use OWM JAPIs?
Do you use Maven, Gradle, or some another build tool? [OWM JAPIs is available in Maven Central repository](http://search.maven.org/#search%7Cga%7C1%7Cowm-japis).

No? Not an issue. You can download the releases (source and binaries) from [OWM JAPIs Downloads](http://go.aksingh.net/owm-japis-dloads).



##Versions
###2.5 (Compatible with OpenWeatherMap.org's API v2.5)


####2.5.3.0

**Major version**

1. Migrated the function calls to OWM Pro APIs from OWM -> OWMPro class
2. Added the support for getting accumulated precipitation and temperature
3. Improved the support for getting historical weather data from OWM APIs
4. Requires Java 7 and supports Android, Kotlin, and Java (as of v2.5.1.0)

NOTE: Please MIGRATE the function calls to OWM Pro APIs from OWM to OWMPro.



##How to use OWM JAPIs?
Anyone with little coding knowledge of Java or Kotlin will feel at home while using this library.  **Identifiers are written to be self-explanatory and APIs' documentation** (java docs) is also provided along with OWM JAPIs.

1. Add this JAR file in your project's libraries:
    1. owm-japis-{VERSION}.jar
2. Write your code as such:
    1. Create and initialize object {obj1} of "OWM" class
    2. Call this object's {obj1} functions to get the desired weather data (such as current weather, daily forecast, etc.).
    3. The data is returned as a new object {obj2} of a compatible class based on the type of asked/retrieved weather data (current weather data comes in a different class's object than daily forecast data).
    3. Call this returned object's {obj2} functions to get the required information from the collective weather data (such as temperature, pressure, wind speed, etc.).

Is it hard to understand the English of programming? Well, you are not the only one. I feel the same. Kindly have a look on the example(s) below for a clear understanding.



##Example
### Basic Example
####Sample Code

    import net.aksingh.owmjapis.core.OWM;
    import net.aksingh.owmjapis.api.APIException;
    import net.aksingh.owmjapis.model.CurrentWeather;
    
    public class OwmJapisExample1 {
    
        public static void main(String[] args)
                throws APIException {
                
            // declaring object of "OWM" class
            OWM owm = new OWM("YOUR-API-KEY-HERE");
    
            // getting current weather data for the "London" city
            CurrentWeather cwd = owm.currentWeatherByCityName("London");
    
            //printing city name from the retrieved data
            System.out.println("City: " + cwd.getCityName());
            
            // printing the max./min. temperature
            System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                                + "/" + cwd.getMainData().getTempMin() + "\'K");
        }
    }

####Output

    City: London
    Temperature: 73.4/68.72 'K


###Advance Example
You can simply use the APIs (as given in the basic example) for learning, testing or experimenting with the functions provided in this library. But it may not be good enough for production or deployment environment. Don't you agree?

Professionally, you should always **write code which can handle errors** as best as possible. OWM JAPIs helps here too by providing checker functions which allows you to **check if a data is available or not**, i.e., that particular data is retrieved and parsed properly or not. Of course, exception handling can still be used, but these functions are really useful and make the retrieved-data-error-handling task very simple.

Using OWM JAPIs, you can always check if a particular data is available or not. This is done by using the **has{DATA-NAME}()** functions. For example, **hasRespCode()** function checks if the retrieved data has a response code or not. And if available, response code can be used to check if the whole data was downloaded and parsed correctly or not, as you can see in the example below.

####Sample Code

    import net.aksingh.owmjapis.core.OWM;
    import net.aksingh.owmjapis.api.APIException;
    import net.aksingh.owmjapis.model.CurrentWeather;
    
    public class OwmJapisExample2 {
    
        public static void main(String[] args)
                throws APIException {
            
            // declaring object of "OWM" class
            OWM owm = new OWM("YOUR-API-KEY-HERE");
    
            // getting current weather data for the "London" city
            CurrentWeather cwd = owm.currentWeatherByCityName("London");
            
            // checking data retrieval was successful or not
            if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
            
                // checking if city name is available
                if (cwd.hasCityName()) {
                    //printing city name from the retrieved data
                    System.out.println("City: " + cwd.getCityName());
                }
                
                // checking if max. temp. and min. temp. is available
                if (cwd.hasMainData() && cwd.getMainData().hasTempMax() && cwd.getMainData().hasTempMin()) {
                    // printing the max./min. temperature
                    System.out.println("Temperature: " + cwd.getMainData().getTempMax()
                                + "/" + cwd.getMainData().getTempMin() + "\'K");
                }
            }
        }
    }

####Output

    City: London
    Temperature: 73.4/68.72 'K



##Source code
Download the library's source code from [OWM JAPIs Source](http://go.aksingh.net/owm-japis-src).



##Bugs / Requests
Got a problem, error or bug in the library? Or want a new feature that's not already available in OWM JAPIs?

Kindly post bugs or feature requests at [OWM JAPIs Issues](http://go.aksingh.net/owm-japis-bugs) and I will try to solve/add it in the next release.



##Developer
**Ashutosh Kumar Singh** | [www.aksingh.net](https://www.aksingh.net/) | [ashutosh@aksingh.net](mailto:ashutosh@aksingh.net)

and contributors. Do you wish to contribute? Just fork this repo on BitBucket or GitHub and send a pull request.


##Credits
1. [OpenWeatherMap.org](https://openweathermap.org/)
for providing free weather data and creating easy-to-use APIs.

2. [ForecastIO-Lib-Java](https://github.com/dvdme/forecastio-lib-java)
for providing ideas like support for third-party Http libraries.

3. [You - for supporting OWM JAPIs](http://go.aksingh.net/owm-japis-bugs)
and reporting bugs, and finding and sharing solutions for them too.



##License
Copyright (c) 2013-2017 Ashutosh Kumar Singh `<ashutosh@aksingh.net>`
  
Released under the terms of the [MIT license](https://opensource.org/licenses/MIT). It's open source and developer-friendly.
