package yaroslavgorbach.reaction.feature.common.ui.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.reaction.feature.common.ui.theme.AppTypography.Companion.popinsSemiBold
import yaroslavgorbach.reaction.feature.common.ui.theme.EerieBlack
import yaroslavgorbach.reaction.feature.common.ui.theme.LightSilver
import yaroslavgorbach.reaction.feature.common.ui.theme.White

@Composable
fun PrimaryLargeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = EerieBlack),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = popinsSemiBold,
            color = White
        )
    }
}

@Composable
fun PrimaryMediumButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = EerieBlack),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsSemiBold,
            color = White
        )
    }
}

@Composable
fun PrimarySmallButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = EerieBlack),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsSemiBold,
            color = White
        )
    }
}

@Composable
fun SecondaryLargeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightSilver),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = popinsSemiBold,
            color = EerieBlack
        )
    }
}

@Composable
fun SecondaryMediumButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightSilver),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsSemiBold,
            color = EerieBlack
        )
    }
}

@Composable
fun SecondarySmallButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = LightSilver),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsSemiBold,
            color = EerieBlack
        )
    }
}

@Composable
fun OutlineMediumButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = EerieBlack),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsSemiBold,
            color = EerieBlack
        )
    }
}

@Composable
fun OutlineLargeButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = EerieBlack),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = popinsSemiBold,
            color = EerieBlack
        )
    }
}