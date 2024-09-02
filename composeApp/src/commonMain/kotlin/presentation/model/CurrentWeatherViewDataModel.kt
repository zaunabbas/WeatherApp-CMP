package presentation.model

import androidx.compose.runtime.Immutable
import data.Constants
import data.model.CurrentWeather
import domain.toCountryName
import domain.toDateTimeAsEEEE_dd_MMMMStringFormat
import presentation.base.ModelMapper
import presentation.base.ViewDataModel
import kotlin.math.round

@Immutable
data class CurrentWeatherViewDataModel(
    val currentTime: String,
    val city: String,
    val country: String,
    val currentTemp: String,
    val humidity: String,
    val wind: String,
    val description: String,
    val visibility: String,
    val realFeel: String,
    val currentIcon: String,
    val currentIconAnimation: Int,
) : ViewDataModel()

class CurrentWeatherMapper : ModelMapper<CurrentWeather, CurrentWeatherViewDataModel> {

    override fun mapperToViewDataModel(dataModel: CurrentWeather): CurrentWeatherViewDataModel {

        val weatherItem = dataModel.weatherItems?.first()

        return CurrentWeatherViewDataModel(
            currentTime = ((dataModel.dt ?: (0 * 1000L)).toDateTimeAsEEEE_dd_MMMMStringFormat()),
            city = dataModel.name?.uppercase() ?: "-",
            country = dataModel.sys?.country?.toCountryName()?.uppercase() ?: "-",
            currentTemp = "${dataModel.main?.temp?.toInt()}",
            humidity = "${dataModel.main?.humidity}%",//${Res.string.percent}",
            wind = "${round(dataModel.wind?.speed ?: 0.0).toInt()} km/h",// ${Res.string.speed}",
            visibility = "${dataModel.visibility?.div(1000)} km",//${Res.string.km}",
            realFeel = "${dataModel.main?.let { round(it.feelsLike).toInt() }}º",//${Res.string.temp}",
            currentIcon = "https://openweathermap.org/img/wn/${weatherItem?.icon}@4x.png"/*String.format(
                Constants.OpenWeather.WEATHER_ICON_URL, weatherItem.icon
            )*/,
            description = weatherItem?.description ?: "--",
            currentIconAnimation = getWeatherAnimation(weatherItem?.id ?: -1)
        )
    }


    private fun getWeatherAnimation(weatherCode: Int): Int {
        /*if (weatherCode / 100 == 2) {
            return R.raw.storm_weather
        } else if (weatherCode / 100 == 3) {
            return R.raw.rainy_weather
        } else if (weatherCode / 100 == 5) {
            return R.raw.rainy_weather
        } else if (weatherCode / 100 == 6) {
            return R.raw.snow_weather
        } else if (weatherCode / 100 == 7) {
            return R.raw.unknown
        } else if (weatherCode == 800) {
            return R.raw.clear_day
        } else if (weatherCode == 801) {
            return R.raw.few_clouds
        } else if (weatherCode == 803) {
            return R.raw.broken_clouds
        } else if (weatherCode / 100 == 8) {
            return R.raw.cloudy_weather
        }
        return R.raw.unknown*/
        return -1
    }
}
