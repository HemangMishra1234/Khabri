package com.project.khabri.ui.journalist

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.khabri.R
import com.project.khabri.ui.components.AppTextField


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
                            .padding(horizontal = 8.dp, vertical = 8.dp),
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
                            Text(
                                text = "Write", modifier = Modifier.padding(vertical = 4.dp),
                                color = if (uiState.currentPage == 0) MaterialTheme.colorScheme.primaryContainer
                                else Color.White
                            )
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
                                text = "Improve", modifier = Modifier.padding(vertical = 4.dp),
                                color = if (uiState.currentPage == 1) MaterialTheme.colorScheme.primaryContainer
                                else Color.White
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
                            viewModel,
                            { viewModel.updateImprovedDescription(it) },

                            whenUseItClicked = {
                                viewModel.updateDescription(uiState.improvedContent)
                                viewModel.updateCurrentPage(0)
                            })
                    } else {
                        WriteScreen(
                            uiState,
                            viewModel = viewModel,
                            onTitleChange = { viewModel.updateTitle(it) },
                            isCategoryExpanded = isCategoryExpanded,
                            onCategoryDismiss = { isCategoryExpanded = false },
                            onCategoryExpanded = { isCategoryExpanded = true },
                            onCategorySelected = { viewModel.updateCategory(it) },
                            isToneExpanded = isToneExpanded,
                            onToneExpanded = { isToneExpanded = true },
                            onToneSelected = { viewModel.updateTone(it) },
                            onToneDismiss = { isToneExpanded = false },
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
                    onClick = {
                        viewModel.uploadImageAndPost(uiState.image)
                    }, modifier = Modifier
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
    viewModel: NewsWritingViewModel,
    onTitleChange: (String) -> Unit,
    isCategoryExpanded: Boolean,
    onCategoryDismiss: () -> Unit,
    onCategoryExpanded: () -> Unit,
    onCategorySelected: (String) -> Unit,
    onToneSelected: (String) -> Unit,
    onToneExpanded: () -> Unit,
    onToneDismiss: () -> Unit,
    isToneExpanded: Boolean
) {
    Column(modifier = Modifier.padding(horizontal = 4.dp)) {
        AppTextField(
            outerText = "Enter the title of your news",
            value = uiState.title, onValueChange = { onTitleChange(it) },
            icon = null,
            placeholderText = "Title",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
            value = uiState.description, onValueChange = { viewModel.updateDescription(it) },
            outerText = "Enter the description of your news",
            placeholderText = "Description",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            icon = null
        )
//    AppTextField(value = uiState, onValueChange = )
//    OutlinedTextField(value = uiState.title, onValueChange = { onTitleChange(it) }, label = {
//        Text(text = "Title")
//    }, modifier = Modifier
//        .fillMaxWidth()
//        .padding(horizontal = 8.dp)
//    )
//    OutlinedTextField(value = uiState., onValueChange = )
        Row {
            FilledTonalButton(onClick = { onCategoryExpanded() }) {
                Text(text = uiState.category)
            }
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
            Spacer(modifier = Modifier.width(8.dp))
            FilledTonalButton(onClick = { onToneExpanded() }) {
                Text(
                    text = uiState.tone,
                    textAlign = TextAlign.Center
                )
            }
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
        Spacer(modifier = Modifier.height(16.dp))
        ImagePicker(onImageSelected = { viewModel.updateImage(it) })
        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            icon = null,
            outerText = "Enter the content of your news",
            placeholderText = "Content",
            value = uiState.content, onValueChange = { viewModel.updateContent(it) },
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        )

    }
}


@Composable
fun ImproveScreen(
    uiState: NewsWritingUIState,
    viewModel: NewsWritingViewModel,
    onImprovedDescriptionChange: (String) -> Unit,
    whenUseItClicked: () -> Unit
) {
    OutlinedTextField(
        value = uiState.prompt,
        onValueChange = { viewModel.updatePrompt(uiState.prompt) },
        label = {
            Text(text = "Prompt")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    AnimatedVisibility(uiState.isLoading) {
        Box(modifier = Modifier.fillMaxWidth()) {

        }
    }
    AnimatedVisibility(visible = !uiState.isLoading) {
        OutlinedTextField(
            value = uiState.improvedContent,
            onValueChange = { onImprovedDescriptionChange(it) },
            label = {
                Text(text = "Content")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }

    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedButton(
            onClick = {
                viewModel.sendImprovement()
            },
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

// Add this function to handle image selection
@Composable
fun ImagePicker(
    onImageSelected: (Bitmap) -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            onImageSelected(bitmap)
        }
    }

    Button(onClick = { launcher.launch("image/*") }) {
        Text(text = "Select Image")
    }
}