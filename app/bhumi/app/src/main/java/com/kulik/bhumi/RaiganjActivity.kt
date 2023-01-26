package com.kulik.bhumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kulik.bhumi.ui.theme.BhumiTheme

class RaiganjActivity : ComponentActivity() {
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
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "The town of Raiganj situated in the narrow strip which joins the Northern and Southern portion of the state of West Bengal, has come up in prominence as a result of partition of India. This town in pre-partition was a small village dependent upon the larger urban centers. The partition of Bengal has suddenly left this town on its own. Other such villages have either shifted their dependence to other town in the state of West Bengal or have started growing themselves. The town of Raiganj belongs to other category. It was merely a mouja under Dinajpur Maharaja Administration and was Bandar meaning a river based hald post of merchentile commodities from far and near village.In pre-independence  days this was rather a neglected area. In 1951 immediately after independence the town barely had a population of 17000 persons. Since then this town has grown up faster.")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    BhumiTheme {
        Greeting("Android")
    }
}