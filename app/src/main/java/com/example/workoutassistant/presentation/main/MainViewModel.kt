package com.example.workoutassistant.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.workoutassistant.base.BaseViewModel
import com.example.workoutassistant.base.Reducer
import com.example.workoutassistant.base.TimeCapsule
import com.example.workoutassistant.data.gymDays
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<MainScreenState, MainScreenUiEvent>() {

    private val reducer = MainReducer(MainScreenState.initial())

    override val state: StateFlow<MainScreenState>
        get() = reducer.state

    val timeMachine: TimeCapsule<MainScreenState>
        get() = reducer.timeCapsule

    init {
        viewModelScope.launch {
            sendEvent(MainScreenUiEvent.ShowData(gymDays))
        }
    }

    private fun sendEvent(event: MainScreenUiEvent) {
        reducer.sendEvent(event)
    }

    fun onItemClicked(id: Int) {
        sendEvent(MainScreenUiEvent.OnItemClicked(id))
    }

    private class MainReducer(initial: MainScreenState) :
        Reducer<MainScreenState, MainScreenUiEvent>(initial) {
        override fun reduce(oldState: MainScreenState, event: MainScreenUiEvent) {
            when (event) {
                is MainScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }
                is MainScreenUiEvent.OnItemClicked -> {
                    // TODO
                }
            }
        }
    }
}
