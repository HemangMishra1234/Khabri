package com.project.pattagobhi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String = "Continue",
    isEnabled: Boolean,
    onClick: () -> Unit = {}
) {
    Button(onClick = { if(isEnabled) onClick()}, enabled = isEnabled, modifier = modifier.widthIn(min = 80.dp)) {
        Text(text = text)
    }
//    Box(
//        modifier = modifier
//            .clickable {
//                if(isEnabled)onClick() }
//
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//                .shadow(
//                    elevation = 4.dp,
//                    spotColor = Color.Black,
//                    ambientColor = Color.Black,
//                    shape = RoundedCornerShape(15.dp)
//                )
//                .shadow(
//                    elevation = 14.dp,
//                    spotColor = Color(0x40050505),
//                    ambientColor = Color(0x40000000)
//                )
//                .fillMaxWidth(0.9f)
//                .clip(shape = RoundedCornerShape(15.dp))
//                .background(color = if(isEnabled) Color(0xfffb8a7a) else
//                    Color(0xFFF3AEA5).copy(alpha = 1.0f))
//                .padding(16.dp)
//        ) {
//            Text(
//                text = text,
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    lineHeight = 24.sp,
//                    fontFamily = FontFamily.Monospace,
//                    fontWeight = FontWeight(600),
//                    color = Color(0xFFEEF6F8),
//                    textAlign = TextAlign.Center,
//                    letterSpacing = 0.32.sp,
//                )
//            )
//        }
}
