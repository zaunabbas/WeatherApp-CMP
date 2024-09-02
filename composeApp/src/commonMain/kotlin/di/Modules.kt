package di

import data.remote.Api
import data.remote.KtorWeatherApi
import domain.repository.WeatherRepository
import data.repository.WeatherRepositoryImpl
import domain.use_case.GetCurrentWeatherUseCase
import domain.use_case.GetHourlyWeatherUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.model.CurrentWeatherMapper
import presentation.model.HourlyWeatherMapper
import presentation.ui.home.HomeScreenModel


val sharedModule = module {

    single {
        val json = Json {
            ignoreUnknownKeys = true
        }
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    //singleOf(::WeatherRepositoryImpl).bind<WeatherRepository>()
    single<Api> { KtorWeatherApi(get()) }
    single<CurrentWeatherMapper> { CurrentWeatherMapper() }
    single<HourlyWeatherMapper> { HourlyWeatherMapper() }
    single<GetCurrentWeatherUseCase> { GetCurrentWeatherUseCase(get()) }
    single<GetHourlyWeatherUseCase> { GetHourlyWeatherUseCase(get()) }


    single {
        WeatherRepositoryImpl(get())
    }.bind<WeatherRepository>()
}

val screenModelsModule = module {
    factoryOf(::HomeScreenModel).bind()
}