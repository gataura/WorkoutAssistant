package com.example.workoutassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutassistant.data.gymDays
import com.example.workoutassistant.domain.entities.GymDayItem
import com.example.workoutassistant.presentation.main.MainViewModel
import com.example.workoutassistant.ui.theme.WorkoutAssistantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAssistantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel = MainViewModel())
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> ContentWithProgress()
        state.data.isNotEmpty() -> MainScreenContent(gymDays = state.data)
    }
}

@Composable
private fun MainScreenContent(gymDays: List<GymDayItem>) {
    Box(modifier = Modifier.padding(16.dp)) {
        LazyColumn(content = {
            items(gymDays) {
                CardItem(text = it.name)
            }
        })
    }
}

@Composable
private fun CardItem(text: String) {
    Text(text = text, modifier = Modifier.padding(16.dp))
}

@Composable
private fun ContentWithProgress() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkoutAssistantTheme {
        MainScreenContent(gymDays)
    }
}
