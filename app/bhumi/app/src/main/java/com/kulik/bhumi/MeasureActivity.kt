package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
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
import com.kulik.bhumi.ui.theme.Purple200

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
        .padding(10.dp)
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Units of measurement", style = TextStyle(fontSize = 28.sp, textDecoration = TextDecoration.Underline))

        Text(text = "1 acre = 100 shatak", style = TextStyle(fontSize = 16.sp))
        Text(text = "1 acre = 0.4 hector", style = TextStyle(fontSize = 16.sp))
        Text(text = "1 katha = 5 shatak", style = TextStyle(fontSize = 16.sp))

        Text(text = "1 kahon = 16 pon", style = TextStyle(fontSize = 16.sp))

        Divider(
            color = Purple200,
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp)
        )


        Text(text = "Distance", style = TextStyle(fontSize = 28.sp, textDecoration = TextDecoration.Underline))
        Text(text = "dalimgaon rail stn - 2.5 km", style = TextStyle(fontSize = 16.sp))
        Text(text = "radhikapur rail stn - 9.5 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "kaliyaganj rail stn - 10.2 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "dhankoil (state highway 10) - 7.6 kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "buniyadpur (NH510) - 31 kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "itahar bus stand - 37.5 kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "durgapur in raiganj (NH12) - 33 kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "siliguri more in raiganj - 33.6 kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "panishala toll plaza (NH12)- 43 kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "malda town stn - 92 km", style = TextStyle(fontSize = 16.sp))
        Text(text = "balurghat - 71 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "gangarampur - 46 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "hili - 99 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "Bagdogra Bihar More- 201kms ", style = TextStyle(fontSize = 16.sp))
        Text(text = "Darjeeling, West Bengal - 268 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "Gangtok, Sikkim - 321 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "kolkata airport - 400 km", style = TextStyle(fontSize = 16.sp))
        Text(text = "Delhi airport - 1502 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "RCP navi mumbai - 2200 km ", style = TextStyle(fontSize = 16.sp))
        Text(text = "Silk Board Bus Stand Bengaluru - 2289 km ", style = TextStyle(fontSize = 16.sp))
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    BhumiTheme {
        Measure()
    }
}