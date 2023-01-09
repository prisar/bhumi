package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kulik.bhumi.ui.theme.BhumiTheme

class LandActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LandDetails()
                }
            }
        }
    }
}

@Composable
fun MapImage() {
    Image(
        painter = painterResource(id = R.drawable.municipality_map),
        contentDescription = "Raiganj Map",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LandDetails() {
    Text(text = "Land details")

    MapImage()
}

@Preview(showBackground = true)
@Composable
fun LandPreview() {
    BhumiTheme {
        LandDetails()
    }
}