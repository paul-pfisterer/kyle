package com.pfisterer.compose.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfisterer.compose.R

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier,
        textAlign = textAlign,
        fontFamily = FontFamily(
            Font(R.font.dmsansregular, weight = FontWeight.Normal),
            Font(R.font.dmsansmedium, weight = FontWeight.Medium),
            Font(R.font.dmsanssemibold, weight = FontWeight.SemiBold),
            Font(R.font.dmsansbold, weight = FontWeight.Bold),
            Font(R.font.dmsansblack, weight = FontWeight.Black),
        )
    )
}

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(vertical = 14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
        ),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}