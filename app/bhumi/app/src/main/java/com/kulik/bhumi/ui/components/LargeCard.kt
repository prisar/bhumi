package com.kulik.bhumi.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/*
* blog
* https://www.valueof.io/blog/animation-compose-api-summary
*
 */

@Composable
fun LargeCard(cardText: String){
    val cardModifier  = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(50.dp)
    Card(elevation = 10.dp, modifier = cardModifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var visible by remember { mutableStateOf(true) }
            val density = LocalDensity.current
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically {
                    // Slide in from 40 dp from the top.
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Text(text = cardText,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp))
            }
        }
    }
}