package com.example.workoutassistant.presentation.main

import androidx.compose.runtime.Immutable
import com.example.workoutassistant.base.UiEvent
import com.example.workoutassistant.base.UiState
import com.example.workoutassistant.domain.entities.GymDayItem

@Immutable
sealed class MainScreenUiEvent : UiEvent {
    data class ShowData(val items: List<GymDayItem>) : MainScreenUiEvent()
    data class OnItemClicked(val id: Int) : MainScreenUiEvent()
}

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: List<GymDayItem>,
) : UiState {

    companion object {
        fun initial() = MainScreenState(
            isLoading = true,
            data = emptyList(),
        )
    }

    override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}"
    }
}