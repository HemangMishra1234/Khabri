package com.project.khabri.ui.journalist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.project.khabri.domain.data.GeminiPrompts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class NewsWritingUIState(
    val category: String = NewsCategories.SCIENCE.displayName,
    val title: String = "",
    val tone: String = ToneOfVoice.AUTHORITATIVE_PROFESSIONAL.displayName,
    val primaryKey: String = "",
    val pov: String = "",
    val prompt: String = GeminiPrompts.WRITE_WITH_GEMINI.prompt+" "+title+" "+tone+" "+category,
    val content: String = "",
    val feedback: String = "",
    val improvedDescription: String = "Your content will appear here...",
    val currentPage: Int = 0,
    val isLoading: Boolean=false
)

class NewsWritingViewModel() : ViewModel() {

    private val _uiState = mutableStateOf(NewsWritingUIState())
    val uiState: State<NewsWritingUIState> = _uiState

    fun updateTitle(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
        _uiState.value = _uiState.value.copy(prompt = GeminiPrompts.WRITE_WITH_GEMINI.prompt+" "+title+" "+_uiState.value.tone+" "+_uiState.value.category)
    }

    fun updatePrompt(prompt: String) {
        _uiState.value = _uiState.value.copy(prompt = prompt)
    }

    fun updateCategory(category: String) {
        _uiState.value = _uiState.value.copy(category = category)
        _uiState.value = _uiState.value.copy(prompt = GeminiPrompts.WRITE_WITH_GEMINI.prompt+" "+_uiState.value.title+" "+_uiState.value.tone+" "+category)
    }

    fun updateTone(tone: String) {
        _uiState.value = _uiState.value.copy(tone = tone)
        _uiState.value = _uiState.value.copy(prompt = GeminiPrompts.WRITE_WITH_GEMINI.prompt+" "+_uiState.value.title+" "+tone+" "+_uiState.value.category)
    }

    fun updateDescription(description: String) {
        _uiState.value = _uiState.value.copy(content = description)

    }

    fun updateCurrentPage(currentPage: Int) {
        _uiState.value = _uiState.value.copy(currentPage = currentPage)
    }

    fun updateImprovedDescription(description: String) {
        _uiState.value = _uiState.value.copy(improvedDescription = description)
    }


    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyAHIgwWHzReB4PWkKZvRtLAl15DocFdOqI"
    )

    fun sendImprovement(

    ) {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        text(_uiState.value.prompt)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = _uiState.value.copy(improvedDescription = outputContent, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(improvedDescription = e.localizedMessage ?: "", isLoading = false)
            }
        }
    }


}