package com.project.khabri.ui.journalist

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
    val image: Uri? = null
)

class NewsWritingViewModel() : ViewModel() {

    private val _uiState = mutableStateOf(NewsWritingUIState())
    val uiState: State<NewsWritingUIState> = _uiState

    fun updateImage(uri: Uri) {
        _uiState.value = _uiState.value.copy(image = uri)
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
        _uiState.value = _uiState.value.copy(description = description)

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

    fun improveGrammer(){
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        text(GeminiPrompts.GRAMMER_IMPROVE.prompt+"  content is: "+_uiState.value.content + ", title is: "+_uiState.value.title + " and description is: "+_uiState.value.description)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = _uiState.value.copy(feedback = outputContent, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(feedback = e.localizedMessage ?: "", isLoading = false)
            }
        }
    }

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
    fun uploadImageAndPost(context: Context, imageUri: Uri) {
        uploadImageToFirebase(context, imageUri){url->
            Log.i("NewsWritingViewModel", "Image uploaded to Firebase: $url")

        }
    }


    // NewsWritingViewModel.kt
    fun uploadImageToFirebase(context: Context, imageUri: Uri, onSuccess: (String)-> Unit) {
        val storageReference = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.jpg")

        storageReference.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                // Get the download URL after the upload is successful
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    Toast.makeText(context, "Upload successful!", Toast.LENGTH_SHORT).show()
                    onSuccess(uri.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "Upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


}