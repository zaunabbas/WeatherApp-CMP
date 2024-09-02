package domain.use_case


import data.DataResource
import data.model.CurrentWeather
import domain.asFlow
import domain.exception.BaseException
import domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FlowUseCase<GetCurrentWeatherUseCase.Params, DataResource<CurrentWeather>>(dispatcher) {

    override fun execute(params: Params?): Flow<DataResource<CurrentWeather>> {

        if (params?.city?.isNotEmpty() == true) {
            return weatherRepository.getCurrentWeatherByCity(params.city)
        }

        return BaseException.AlertException(
            -1,
            "City name input invalid"/*context.getString(R.string.city_input_invalid)*/
        ).asFlow()
    }

    data class Params(val city: String)
}