package com.project.khabri.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.project.khabri.R
import com.project.khabri.ui.feed.FeedViewModel
import com.project.khabri.ui.feed.ListView

//Pinggy, NGROK
@Composable
fun Profile(
    viewModel: FeedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        val auth = FirebaseAuth.getInstance().currentUser
        val articles by viewModel.savedArticles.collectAsState(initial = emptyList())
        Column(modifier = Modifier.fillMaxWidth()) {

//                Spacer(modifier = Modifier.height(20.dp))
//                Text(text = it.toString())
//                Text(text = it.toString())umn(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = auth?.photoUrl ?: Icons.Default.Person, contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )


                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = auth?.displayName ?: "User",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center


                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "My Bookmarks",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                ListView(
                    articles = articles,
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth(),
                    saveArticle = { viewModel.saveArticle(it) },
                    unSaveArticle = { viewModel.unSaveArticle(it) },
                ) {

                }
            }

        }



}