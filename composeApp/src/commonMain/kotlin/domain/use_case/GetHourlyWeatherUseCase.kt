package domain.use_case

import data.data
import data.model.CurrentWeather
import domain.asFlow
import domain.exception.BaseException
import domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class GetHourlyWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FlowUseCase<GetHourlyWeatherUseCase.Params, GetHourlyWeatherUseCase.Response>(dispatcher) {

    override fun execute(parameters: Params?): Flow<Response> {

        if (parameters != null) {
            return weatherRepository.getHourlyWeather(parameters.lat, parameters.long)
                .map { it.data?.hourly ?: emptyList() }
                .map { hourly ->
                    Response(
                        today = hourly//hourly.filter { (it.dt ?: 0L) <= maxToday() }
                    )
                }
        }

        return BaseException.AlertException(
            -1,
            "Lat, long invalid"/*context.getString(R.string.lat_lon_invalid)*/
        ).asFlow()
    }

    private fun maxToday(): Long {
        val nextDay = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        //nextDay.date.plus(1, DateTimeUnit.DAY)

        return LocalDateTime(
            year = nextDay.year,
            month = nextDay.month,
            dayOfMonth = nextDay.dayOfMonth,
            hour = 6,
            minute = 0,
            second = 0,
            nanosecond = 0
        ).time.toMillisecondOfDay() / 1000L
    }

    data class Params(val lat: Double, val long: Double)

    data class Response(val today: List<CurrentWeather>)

}