import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.theme.WeatherAppTheme
import presentation.ui.home.HomeScreen

@Composable
@Preview
fun App() {
    WeatherAppTheme{
        Navigator(HomeScreen)
    }
}