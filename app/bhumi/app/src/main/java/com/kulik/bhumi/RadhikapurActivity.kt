package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kulik.bhumi.ui.theme.BhumiTheme

class RadhikapurActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting4("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Radhikapur railway station is a station serving Radhikapur in the Uttar Dinajpur district, West Bengal, India. It is an active rail transit system on the Bangladesh–India border. ")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    BhumiTheme {
        Greeting4("Android")
    }
}