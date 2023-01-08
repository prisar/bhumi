package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kulik.bhumi.ui.theme.BhumiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
//    Text(text = "Hello $name!")
    val shape = CircleShape

    Column (verticalArrangement= Arrangement.SpaceBetween) {
        Text(
            text = "The maps",
            style = TextStyle( color = White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                        .border(2.dp, MaterialTheme.colors.secondary, shape)
                        .background(MaterialTheme.colors.primary, shape)
                        .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, false)

        ) {
            Text("Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BhumiTheme {
        Greeting("Android")
    }
}