package com.project.khabri.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.project.khabri.ui.feed.FeedViewModel
import com.project.khabri.ui.feed.HomeScreen
import com.project.khabri.ui.journalist.NewsWriting
import com.project.khabri.ui.journalist.NewsWritingViewModel
import com.project.khabri.ui.profile.Profile
import com.project.pattagobhi.ui.home.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: FeedViewModel,newsWritingViewModel: NewsWritingViewModel){
    val uiState by viewModel.uiState
    val pagerState = rememberPagerState(pageCount = {
        NavigationItem.entries.size
    })
    val scope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationItem.entries.forEachIndexed(){ index, item->
                    NavigationBarItem(selected = pagerState.currentPage==index , onClick = { scope.launch {
                        pagerState.animateScrollToPage(index)
                    } }, icon = {
                        if(pagerState.currentPage==index)
                        Icon(imageVector = item.iconSelected, contentDescription = null)
                        else Icon(imageVector = item.iconNotSelected, contentDescription = null)
                    }, label = {
                        Text(text = item.displayName)
                    })
                }
            }

        }


    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()){

            HorizontalPager(state = pagerState) {page->
                when(NavigationItem.entries[page]){
                    NavigationItem.HOME -> HomeScreen(Modifier,uiState.articles,viewModel){
                        viewModel.likeArticle(it)
                    }
                    NavigationItem.PROFILE -> Profile()
                    NavigationItem.ADDNEWS -> NewsWriting(viewModel = newsWritingViewModel)
                }

            }
        }

    }
}


//@Composable
//fun Profile(){
//    Box(modifier = Modifier.fillMaxSize())
//
//}

@Composable
fun Calender(){
    Box(modifier = Modifier.fillMaxSize())
}

