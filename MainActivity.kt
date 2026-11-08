package com.example.campuscardlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                SimpleGreetingScreen()
            }
        }
    }
}

@Composable
fun SimpleGreetingScreen() {

    val data = listOf(
        listOf("Слово 1", "Слово 2", "Слово 3"),
        listOf("Слово 4", "Слово 5", "Слово 6"),
        listOf("Слово 7", "Слово 8", "Слово 9"),
        listOf("Слово 10", "Слово 11", "Слово 12")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        data.forEachIndexed { rowIndex, words ->
            val rowNum = rowIndex + 1
            val isEven = rowNum % 2 == 0 // Проверка на четный ряд


            val isYellowList = remember(rowIndex) {
                if (isEven) {
                    val count = Random.nextInt(1, words.size + 1)
                    val selected = words.indices.shuffled().take(count)
                    words.indices.map { it in selected }
                } else {
                    words.map { false }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (isEven) Color.LightGray else Color.Transparent)
                    .padding(vertical = 4.dp)
            ) {
                words.forEachIndexed { colIndex, word ->
                    var text by remember { mutableStateOf(word) }

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .background(if (isYellowList[colIndex]) Color.Magenta else Color.White)
                    )
                }
            }
        }
    }
}