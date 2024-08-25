package com.project.khabri.domain


sealed interface APIResponse<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : APIResponse<D, E>
    data class Error<out D, out E : RootError>(val error: E, val data: D? = null) : APIResponse<D, E>
}