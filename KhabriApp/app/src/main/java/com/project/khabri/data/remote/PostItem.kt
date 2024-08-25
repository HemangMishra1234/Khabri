package com.project.khabri.data.remote

import kotlinx.serialization.Serializable


@Serializable
data class PostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
){
    fun print(){

    }
}