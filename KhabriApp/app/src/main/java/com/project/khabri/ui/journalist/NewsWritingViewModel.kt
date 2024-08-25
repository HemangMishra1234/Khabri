package com.project.khabri.ui.journalist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class NewsWritingUIState(
    val category: String = NewsCategories.SCIENCE.displayName,
    val title: String = "",
    val tone: String = ToneOfVoice.AUTHORITATIVE_PROFESSIONAL.displayName,
    val primaryKey: String = "",
    val pov: String = "",
    val description: String = "",
    val feedback: String = "",
    val improvedDescription: String = "For testing",
    val currentPage: Int = 0
)

class NewsWritingViewModel() : ViewModel() {

    private val _uiState = mutableStateOf(NewsWritingUIState())
    val uiState: State<NewsWritingUIState> = _uiState

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun updateCategory(category: String) {
        _uiState.value = _uiState.value.copy(category = category)
    }

    fun updateTone(tone: String) {
        _uiState.value = _uiState.value.copy(tone = tone)
    }

    fun updateDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description)

    }

    fun updateCurrentPage(currentPage: Int) {
        _uiState.value = _uiState.value.copy(currentPage = currentPage)
    }

    fun updateImprovedDescription(description: String) {
        _uiState.value = _uiState.value.copy(improvedDescription = description)
    }


}