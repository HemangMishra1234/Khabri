package com.project.pattagobhi.ui.home

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun MainScreen(){
    val pagerState = rememberPagerState(pageCount = {
        NavigationItem.entries.size
    })
    val scope = rememberCoroutineScope()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationItem.entries.forEachIndexed(){index,item->
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
                    NavigationItem.HOME -> HomeScreen()
                    NavigationItem.PROFILE -> Profile()
                    NavigationItem.CALENDER -> Calender()
                }

            }
        }

    }
}

@Composable
fun HomeScreen(){
    Box(modifier = Modifier.fillMaxSize())
}

@Composable
fun Profile(){
    Box(modifier = Modifier.fillMaxSize())

}

@Composable
fun Calender(){
    Box(modifier = Modifier.fillMaxSize())
}

