package presentation.ui.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.DataResource
import data.model.CurrentWeather
import data.onError
import data.onSuccess
import domain.repository.WeatherRepository
import domain.exception.BaseException
import domain.use_case.GetCurrentWeatherUseCase
import domain.use_case.GetHourlyWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.base.ViewState
import presentation.base.toBaseException
import presentation.model.CurrentWeatherMapper
import presentation.model.CurrentWeatherViewDataModel
import presentation.model.HourlyWeatherMapper
import presentation.model.HourlyWeatherViewDataModel

sealed interface WeatherState {
    val weathers: List<HourlyWeatherViewDataModel>

    data class Today(
        override val weathers: List<HourlyWeatherViewDataModel> = emptyList(),
    ) : WeatherState
}

sealed interface SearchState {
    val enabled: Boolean
    val query: String

    data class Changing(
        override val enabled: Boolean = true,
        override val query: String = ""
    ) : SearchState

    data class Closed(
        override val enabled: Boolean = false,
        override val query: String = ""
    ) : SearchState
}

data class HomeViewState(
    override val isLoading: Boolean = false,
    override val exception: BaseException? = null,
    val currentWeather: CurrentWeatherViewDataModel? = null,
    val weatherState: WeatherState = WeatherState.Today(),
    val searchState: SearchState = SearchState.Closed()
) : ViewState(isLoading, exception)

class HomeScreenModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val weatherMapper: CurrentWeatherMapper,
    private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase,
    private val hourlyWeatherMapper: HourlyWeatherMapper
) : ScreenModel {

    private val _state = MutableStateFlow(HomeViewState(isLoading = true))
    val state: StateFlow<HomeViewState>
        get() = _state

    private val coordinate = MutableStateFlow(Pair(0.0, 0.0))

    private val todayState = MutableStateFlow(WeatherState.Today())

    init {
        getWeather("Karachi")
    }

    fun getWeather(city: String) {
        _state.update { HomeViewState(isLoading = true) }

        CoroutineScope(Dispatchers.Default).launch {

            getCurrentWeatherUseCase.invoke(
                GetCurrentWeatherUseCase.Params(city = city)
            ).collect { result ->
                result.onSuccess {
                    val lat = data.coord?.lat ?: 0.0
                    val lon = data.coord?.long ?: 0.0

                    coordinate.update {
                        it.copy(
                            first = lat,
                            second = lon
                        )
                    }

                    getHourlyWeathers(lat, lon)
                    weatherMapper.mapperToViewDataModel(data)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            currentWeather = data.let { it1 ->
                                weatherMapper.mapperToViewDataModel(
                                    it1
                                )
                            }
                        )
                    }

                }.onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            exception = this.exception.toBaseException()
                        )
                    }
                }
            }

        }
    }

    /**
     * Notify that the user when typing the search input
     */
    fun onSearchInputChanged(searchInput: String) {
        _state.update {
            it.copy(searchState = SearchState.Changing(query = searchInput))
        }
    }

    /**
     * Enable or disable search view
     */
    fun enableSearchView(enabled: Boolean) {
        _state.update { state ->
            state.copy(
                searchState = if (enabled) SearchState.Changing() else SearchState.Closed(
                    query = state.searchState.query
                )
            )
        }
    }

    private fun getHourlyWeathers(lat: Double, long: Double) {
        CoroutineScope(Dispatchers.Default).launch {
            getHourlyWeatherUseCase.invoke(GetHourlyWeatherUseCase.Params(lat, long))
                .catch { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            exception = throwable.toBaseException()
                        )
                    }
                }
                .map { response ->
                    response.today.map { hourlyWeatherMapper.mapperToViewDataModel(it) }
                }
                .collect { todayList ->
                    todayState.update { WeatherState.Today(weathers = todayList) }
                    _state.update { it.copy(weatherState = todayState.value) }
                }
        }
    }
}
