package presentation.model

import data.Constants
import data.model.CurrentWeather
import domain.toDateTimeString
import presentation.base.ModelMapper
import presentation.base.ViewDataModel
import kotlin.math.round

data class HourlyWeatherViewDataModel(
    val dt: Long,
    val hour: String,
    val temp: String,
    val humidity: String,
    val wind: String,
    val visibility: String,
    val realFeel: String,
    val icon: String
) : ViewDataModel()

class HourlyWeatherMapper : ModelMapper<CurrentWeather, HourlyWeatherViewDataModel> {

    override fun mapperToViewDataModel(dataModel: CurrentWeather): HourlyWeatherViewDataModel {
        return HourlyWeatherViewDataModel(
            dt = dataModel.dt ?: 0,
            hour = ((dataModel.dt ?: 0) * 1000L).toDateTimeString(
                Constants.DateFormat.HH_mm,
                zone = null
            ),
            temp = "${dataModel.main?.let { round(it.temp).toInt() }}",
            humidity = "${dataModel.main?.humidity}%",//${Res.string.percent}",
            wind = "${dataModel.wind?.let { round(it.speed).toInt() }} km/h",// ${Res.string.speed}",
            visibility = "${dataModel.visibility?.div(1000)} km",// ${Res.string.km}",
            realFeel = "${dataModel.main?.let { round(it.feelsLike).toInt() }}º",//${Res.string.temp}",
            icon = "https://openweathermap.org/img/wn/${dataModel.weatherItems?.first()?.icon}.png"/*String.format(
                Constants.OpenWeather.WEATHER_SMALL_ICON_URL,
                dataModel.weatherItems.first().icon
            )*/
        )
    }
}
