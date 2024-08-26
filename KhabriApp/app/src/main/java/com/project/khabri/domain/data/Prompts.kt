package com.project.khabri.domain.data

enum class GeminiPrompts(val prompt: String){
    GRAMMER_IMPROVE("improve grammar of the following text while preserving the tone of writer:"),
    WRITE_WITH_GEMINI("use the provided title, tone and category to write a news article with word limit of 100 words"),
    SUMMARY_WITH_GEMINI("summarize the following news article in three or four sentences: ")

}