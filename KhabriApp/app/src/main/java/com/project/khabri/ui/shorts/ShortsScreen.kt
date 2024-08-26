package com.project.khabri.ui.shorts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun NewsArticlePager(articles: List<Article>) {
    val state = rememberPagerState(pageCount = { articles.size })
    VerticalPager(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        flingBehavior = PagerDefaults.flingBehavior(state),
    ) { page ->
        NewsArticleCard(article = articles[page])
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticleCard(article: Article) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()) {

                Card(
                    elevation = CardDefaults.cardElevation(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .align(Alignment.Center)
                )
                {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = rememberImagePainter(article.imageUrl),
                                contentDescription = "Article Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Title
                        Text(
                            text = article.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Description
                        Text(
                            text = article.description,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Footer (Sources)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_sources),
//                contentDescription = "Sources",
//                tint = Color.Unspecified,
//                modifier = Modifier.size(24.dp)
//            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${article.sourcesCount} sources",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                Column {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ThumbDown, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Bookmark, contentDescription = null)
                    }
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(imageVector = Icons.Default.StarRate, contentDescription = null)
                    }
                }

            }
        }
    }
}

// Sample Data Model
data class Article(
    val title: String,
    val description: String,
    val imageUrl: String,
    val sourcesCount: Int
)

// Sample Data
val sampleArticles = listOf(
    Article(
        title = "Keith Haring Mural Faces Demolition",
        description = "A beloved Keith Haring mural in Manhattan's West Village faces an uncertain future...",
        imageUrl = "https://link_to_image.com/image.jpg",
        sourcesCount = 4
    ),
    Article(
        title = "slknoinsdin",
        description = "oinsdiunsidbfds",
        imageUrl = "insdi",
        sourcesCount = 8852
    ),
    Article(
        title = "Keith Haring Mural Faces Demolition",
        description = "A beloved Keith Haring mural in Manhattan's West Village faces an uncertain future...",
        imageUrl = "https://link_to_image.com/image.jpg",
        sourcesCount = 4
    ),
    Article(
        title = "Keith Haring Mural Faces Demolition",
        description = "A beloved Keith Haring mural in Manhattan's West Village faces an uncertain future...",
        imageUrl = "https://link_to_image.com/image.jpg",
        sourcesCount = 4
    ),
    Article(
        title = "Keith Haring Mural Faces Demolition",
        description = "A beloved Keith Haring mural in Manhattan's West Village faces an uncertain future...",
        imageUrl = "https://link_to_image.com/image.jpg",
        sourcesCount = 4
    )
    // Add more articles here
)

@Composable
fun NewsScreen() {
    NewsArticlePager(articles = sampleArticles)
}
