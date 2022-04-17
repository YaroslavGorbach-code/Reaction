package yaroslavgorbach.reaction.feature.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Toolbar(onBack: () -> Unit) {
    TopAppBar(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
        Box(Modifier.fillMaxWidth()) {
            Icon(
                Icons.Outlined.Close,
                "Close",
                modifier = Modifier
                    .clickable { onBack() }
                    .padding(4.dp)
                    .size(30.dp)
                    .align(Alignment.CenterEnd))
        }
    }
}
