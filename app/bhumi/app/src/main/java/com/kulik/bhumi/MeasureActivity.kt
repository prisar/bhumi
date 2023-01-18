package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kulik.bhumi.ui.theme.BhumiTheme

class MeasureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Measure()
                }
            }
        }
    }
}

@Composable
fun Measure() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Units of measurement", style = TextStyle(fontSize = 28.sp, textDecoration = TextDecoration.Underline))

        Text(text = "1 acre = 100 shatak", style = TextStyle(fontSize = 24.sp))
        Text(text = "1 acre = 0.4 hector", style = TextStyle(fontSize = 24.sp))
        Text(text = "1 katha = 5 shatak", style = TextStyle(fontSize = 24.sp))

        Text(text = "1 kahon = 16 pon", style = TextStyle(fontSize = 24.sp))
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    BhumiTheme {
        Measure()
    }
}