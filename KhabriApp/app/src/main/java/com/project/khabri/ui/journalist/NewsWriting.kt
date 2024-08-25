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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.lifecycle.ViewModel
import com.project.khabri.R


enum class NewsCategories(val value: String, val displayName: String, val icon: ImageVector) {
    BUSINESS("business", "Business", Icons.Default.CurrencyBitcoin),
    ENTERTAINMENT("entertainment", "Entertainment", Icons.Default.Movie),
    HEALTH("health", "Health", Icons.Default.HealthAndSafety),
    SCIENCE("science", "Science", Icons.Default.Science),
    SPORTS("sports", "Sports", Icons.Default.SportsCricket),
    TECHNOLOGY("technology", "Technology", Icons.Default.Laptop)
}

enum class ToneOfVoice(val displayName: String) {
    OBJECTIVE_NEUTRAL("Objective and Neutral"),
    AUTHORITATIVE_PROFESSIONAL("Authoritative and Professional"),
    CONCISE_INFORMATIVE("Concise and Informative"),
    ENGAGING_CONVERSATIONAL("Engaging and Conversational"),
    URGENT_COMPELLING("Urgent and Compelling"),
    EMPATHETIC_REASSURING("Empathetic and Reassuring"),
    INQUISITIVE_THOUGHT_PROVOKING("Inquisitive and Thought-Provoking")
}


@Composable
fun NewsWriting(
    onClickFeedback: () -> Unit = {},
    onClickPost: () -> Unit = {},
    viewModel: NewsWritingViewModel
) {
    val uiState by viewModel.uiState
    val scrollState = rememberScrollState()
    var isCategoryExpanded by remember {
        mutableStateOf(false)
    }
    var isToneExpanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)


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
                        text = "khabri",
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
                                    if (uiState.currentPage == 0) Color.White
                                    else Color.Transparent
                                )
                                .clickable {
                                    viewModel.updateCurrentPage(0)
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
                                    if (uiState.currentPage == 1) Color.White
                                    else Color.Transparent
                                )
                                .padding(start = 8.dp)
                                .clickable { viewModel.updateCurrentPage(1) },
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
                    if (uiState.currentPage == 1) {
                        ImproveScreen(
                            uiState,
                            { viewModel.updateImprovedDescription(it) },
                            whenUseItClicked = {
                                viewModel.updateDescription(uiState.improvedDescription)
                                viewModel.updateCurrentPage(0)
                            })
                    } else {
                        WriteScreen(
                            uiState,
                            onTitleChange = { viewModel.updateTitle(it) },
                            isCategoryExpanded = isCategoryExpanded,
                            onCategoryDismiss = { isCategoryExpanded = false },
                            onCategoryExpanded = { isCategoryExpanded = true },
                            onCategorySelected = { viewModel.updateCategory(it) },
                            isToneExpanded = isToneExpanded,
                            onToneExpanded = { isToneExpanded = true },
                            onToneSelected = { viewModel.updateTone(it) },
                            onToneDismiss = { isToneExpanded = false },
                            onDescriptionChange = { viewModel.updateDescription(it) }
                        )
                    }
                }
            }
        }
        if (uiState.currentPage == 0)
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
fun WriteScreen(
    uiState: NewsWritingUIState,
    onTitleChange: (String) -> Unit,
    isCategoryExpanded: Boolean,
    onCategoryDismiss: () -> Unit,
    onCategoryExpanded: () -> Unit,
    onCategorySelected: (String) -> Unit,
    onToneSelected: (String) -> Unit,
    onToneExpanded: () -> Unit,
    onToneDismiss: () -> Unit,
    isToneExpanded: Boolean,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(value = uiState.title, onValueChange = { onTitleChange(it) }, label = {
        Text(text = "Title")
    })
    Row(modifier = Modifier.padding(8.dp)) {
        Text(text = "Category :")
        Column {
            Text(text = uiState.category,
                modifier = Modifier
                    .clickable { onCategoryExpanded() }
            )
            DropdownMenu(expanded = isCategoryExpanded, onDismissRequest = onCategoryDismiss) {
                NewsCategories.entries.forEach { category ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Icon(imageVector = category.icon, contentDescription = null)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = category.displayName)


                            }
                        },
                        onClick = {
                            onCategorySelected(category.displayName)
                            onCategoryDismiss()
                        })
                }

            }

        }

    }
    Row(modifier = Modifier.padding(8.dp)) {
        Text(text = "Tone Of Voice :")
        Column {
            Text(text = uiState.tone,
                modifier = Modifier
                    .clickable { onToneExpanded() }
            )
            DropdownMenu(expanded = isToneExpanded, onDismissRequest = onToneDismiss) {
                ToneOfVoice.entries.forEach { tone ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Text(text = tone.displayName)
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }, onClick = {
                            onToneSelected(tone.displayName)
                            onToneDismiss()
                        })
                }

            }

        }

    }

    OutlinedTextField(value = uiState.description, onValueChange = { onDescriptionChange(it) },
        label = {
            Text(text = "Description")
        })

}


@Composable
fun ImproveScreen(
    uiState: NewsWritingUIState,
    onImprovedDescriptionChange: (String) -> Unit,
    whenUseItClicked: () -> Unit
) {
    OutlinedTextField(
        value = uiState.improvedDescription,
        onValueChange = { onImprovedDescriptionChange(it) })

    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .weight(1f)

        ) {
            Text(text = "Regenerate")
        }
        Spacer(modifier = Modifier.width(4.dp))
        Button(
            onClick = { whenUseItClicked() }, modifier = Modifier
                .weight(1f)

        ) {
            Text(text = "Use it")
        }
    }
}


@Preview
@Composable
fun PreviewNewsWriting() {

}