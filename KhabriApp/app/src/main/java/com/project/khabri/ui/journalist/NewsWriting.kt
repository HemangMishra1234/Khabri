package com.project.khabri.ui.journalist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.khabri.R


enum class NewsCategories(val value: String, val displayName: String, val icon: ImageVector) {
    BUSINESS("business", "Business", Icons.Default.CurrencyBitcoin),
    ENTERTAINMENT("entertainment", "Entertainment", Icons.Default.Movie),
    HEALTH("health", "Health", Icons.Default.HealthAndSafety),
    SCIENCE("science", "Science", Icons.Default.Science),
    SPORTS("sports", "Sports", Icons.Default.SportsCricket),
    TECHNOLOGY("technology", "Technology", Icons.Default.Laptop)
}

@Composable
fun NewsWriting(
    onClickFeedback: () -> Unit,
    onClickPost: () -> Unit
) {

    var isScreenImprove by remember {
        mutableStateOf(false)
    }
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp)


    ) {
        Column {
            Column {
                Row(
                    modifier = Modifier.padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bird_icon_bg_remove),
                        contentDescription = null,
                        modifier = Modifier
                            .size(56.dp)
                    )
                    Text(
                        text = "khabari",
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.headlineLarge
                    )


                }
                Box(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(end = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (!isScreenImprove) Color.White
                                    else Color.Transparent
                                )
                                .clickable {
                                    isScreenImprove = false
                                },
                            contentAlignment = Alignment.Center


                        ) {
                            Text(text = "Write", modifier = Modifier.padding(vertical = 4.dp))
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isScreenImprove) Color.White
                                    else Color.Transparent
                                )
                                .padding(start = 8.dp)
                                .clickable { isScreenImprove = true },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Improve", modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)
            )
            {

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                ) {
                    if (isScreenImprove) ImproveScreen()
                    else WriteScreen()
                }
            }
        }
        Row(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .height(60.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = { onClickFeedback() },
                modifier = Modifier
                    .weight(1f)

            ) {
                Text(text = "Feedback")
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = { onClickPost() }, modifier = Modifier
                    .weight(1f)

            ) {
                Text(text = "Post")
            }
        }
    }
}


@Composable
fun WriteScreen() {


}



@Composable
fun ImproveScreen() {
    Text(text = "This is improve screen.")
}


@Preview
@Composable
fun PreviewNewsWriting() {
    NewsWriting({}, {})
}