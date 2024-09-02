package presentation.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Background(
    modifier: Modifier = Modifier,
    background: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize(align = Alignment.BottomCenter)
            .background(background)
    ) {
        content()
    }
}
