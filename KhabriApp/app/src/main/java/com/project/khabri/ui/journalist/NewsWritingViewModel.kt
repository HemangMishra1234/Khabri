package com.project.khabri.ui.journalist

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.project.khabri.domain.data.GeminiPrompts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.UUID

data class NewsWritingUIState(
    val category: String = NewsCategories.SCIENCE.displayName,
    val title: String = "",
    val tone: String = ToneOfVoice.AUTHORITATIVE_PROFESSIONAL.displayName,
    val primaryKey: String = "",
    val pov: String = "",
    val prompt: String = GeminiPrompts.WRITE_WITH_GEMINI.prompt+" "+title+" "+tone+" "+category,
    val description: String = "",
    val content: String = "",
    val feedback: String = "",
    val improvedContent: String = "Your content will appear here...",
    val currentPage: Int = 0,
    val isLoading: Boolean=false,
    val image: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
)

class NewsWritingViewModel() : ViewModel() {

    private val _uiState = mutableStateOf(NewsWritingUIState())
    val uiState: State<NewsWritingUIState> = _uiState

    fun updateImage(bitmap: Bitmap) {
        _uiState.value = _uiState.value.copy(image = bitmap)
    }

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

    fun updateContent(content: String){
        _uiState.value = _uiState.value.copy(content = content)
    }

    fun updateCurrentPage(currentPage: Int) {
        _uiState.value = _uiState.value.copy(currentPage = currentPage)
        if(currentPage == 1){
            sendImprovement()
        }
    }

    fun updateImprovedDescription(description: String) {
        _uiState.value = _uiState.value.copy(improvedContent = description)
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
                    _uiState.value = _uiState.value.copy(improvedContent = outputContent, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(improvedContent = e.localizedMessage ?: "", isLoading = false)
            }
        }
    }

    // NewsWritingViewModel.kt
    fun uploadImageAndPost(bitmap: Bitmap) {
        uploadImageToFirebase(bitmap, onSuccess = { imageUrl ->
            Log.i("News View Model", "Image uploaded successfully $imageUrl")
        }, onFailure = { exception ->
            // Handle the error
            Log.i("News View Model", "Unable to upload image")
        })
    }


    // NewsWritingViewModel.kt
    private fun uploadImageToFirebase(bitmap: Bitmap, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener { exception ->
            onFailure(exception)
        }.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                onSuccess(uri.toString())
            }
        }
    }


}