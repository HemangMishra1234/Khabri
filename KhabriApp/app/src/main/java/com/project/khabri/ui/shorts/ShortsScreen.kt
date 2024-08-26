package com.project.khabri.ui.shorts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun NewsArticlePager(articles: List<Article>) {
    val state = rememberPagerState(pageCount = {articles.size})
    VerticalPager(
        state = state,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        flingBehavior = PagerDefaults.flingBehavior(state),
    ) { page ->
        NewsArticleCard(article = articles[page])
    }
}

@Composable
fun NewsArticleCard(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
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
    // Add more articles here
)

@Composable
fun NewsScreen() {
    NewsArticlePager(articles = sampleArticles)
}
