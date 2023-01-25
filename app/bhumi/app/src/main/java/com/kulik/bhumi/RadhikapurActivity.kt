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
    Text(text = "Trade volume in radhikapur railway transport is around 80 crores. It has increased significantly since 2017 and is expected to cross 200 crores in comming years.")
    Text(text = "General Manager of NFR has publicly told that the station's infrastructure like platform, canteen, parking will be improved. And there is a possiblity of connecting via passenger trains. Both side of the border share same culture, so it will defintely give a boost to local comminity.")
    Text(text = "Locals have also demanded for addition more trains especially the discontinued delhi and a train to south india.")
    Text(text = "Radhikapur has gattered tremendous attention in recent years. Every days flocks of people from towns come to visit which only a decade ago was totaly neglected.")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    BhumiTheme {
        Greeting4("Android")
    }
}
