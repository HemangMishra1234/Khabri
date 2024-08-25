package com.project.khabri.ui.journalist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class NewsWritingUIState(
    val category: String="",
    val title: String="",
    val tone: String="",
    val primaryKey: String="",
    val pov: String="",
    val description: String="",
    val feedback: String="",
    val improved: String="",
    val currentPage: Int=0
)

class NewsWritingViewModel():ViewModel(){

    private val _uiState= mutableStateOf(NewsWritingUIState())
    val uiState: State<NewsWritingUIState> = _uiState




}