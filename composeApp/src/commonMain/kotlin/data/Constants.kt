package data


object Constants {
    object DateFormat {
        const val EEEE_dd_MMMM = "EEEE',' dd MMMM"
        const val DEFAULT_FORMAT = "dd-mm-yyyy"
        const val HH_mm = "HH:mm"
    }

    object OpenWeather {
        const val WEATHER_ICON_URL = "https://openweathermap.org/img/wn/%s@4x.png"
        const val WEATHER_SMALL_ICON_URL = "https://openweathermap.org/img/wn/%s.png"
        const val WEATHER_UNITS = "metric"
        const val APP_ID = "33d082de51ed2b6397f397eed2c763fc"
        const val API_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}