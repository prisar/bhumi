package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kulik.bhumi.ui.theme.BhumiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhumiTheme {
                Greeting2("Android")
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Scaffold { paddingValues: PaddingValues ->
        AppNavGraph(modifier = Modifier.padding(paddingValues))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    BhumiTheme {
        Greeting2("Android")
    }
}