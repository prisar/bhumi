package com.kulik.bhumi

import android.animation.TimeInterpolator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kulik.bhumi.ui.theme.BhumiTheme
import com.kulik.bhumi.ui.theme.Teal200
import com.kulik.bhumi.ui.theme.Yellow

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhumiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    About()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VisibilityAnimationSample(
    show : Boolean,
    updateVisibility : () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(
                visible = show,
                enter = scaleIn(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = BounceInterpolator().toEasing()
                    )
                ),
                exit = scaleOut(animationSpec = tween(durationMillis = 1000))
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Fab Button",
                        tint = Color.White
                    )

                }
            }
            AnimatedVisibility(
                visible = show,
                enter = scaleIn(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = BounceInterpolator().toEasing()
                    )
                ),
                exit = scaleOut(animationSpec = tween(durationMillis = 1000))
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Fab Button",
                        tint = Color.White
                    )

                }
            }
            AnimatedVisibility(
                visible = show,
                enter = scaleIn(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = BounceInterpolator().toEasing()
                    )
                ),
                exit = scaleOut(animationSpec = tween(durationMillis = 1000))
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Fab Button",
                        tint = Color.White
                    )

                }
            }
            AnimatedVisibility(
                visible = show,
                enter = scaleIn(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = BounceInterpolator().toEasing()
                    )
                ),
                exit = scaleOut(animationSpec = tween(durationMillis = 1000))
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Fab Button",
                        tint = Color.White
                    )

                }
            }
            AnimatedVisibility(
                visible = show,
                enter = scaleIn(
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = BounceInterpolator().toEasing()
                    )
                ),
                exit = scaleOut(animationSpec = tween(durationMillis = 1000))
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Fab Button",
                        tint = Color.White
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { updateVisibility() },
            colors = buttonColors(backgroundColor = Teal200)
        ) {
            Text(text = if (show) "Five Stars!" else "Rate", style = TextStyle(color = Color.Black) )
        }
    }

}

private fun TimeInterpolator.toEasing() = Easing {
    x -> getInterpolation(x)
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun About() {
    val context = LocalContext.current

    Column (verticalArrangement= Arrangement.SpaceBetween) {

        var showText by remember {
            mutableStateOf(false)
        }
        Row(modifier = Modifier.background(color = Yellow)) {
            VisibilityAnimationSample(showText, { showText = !showText })
        }


        Row(modifier = Modifier.padding(8.dp)) {
            Column {
                Text(
                    text = "Important Disclaimer:",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "This app is not affiliated with any government agency or authority. The information provided in this app is unofficial and sourced from publicly available data of West Bengal. Users are strongly advised to verify the accuracy of the information with official government sources for authenticity and official use.",
                    style = MaterialTheme.typography.body1
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center) {
            var count by remember { mutableStateOf(0) }
            Button(onClick = {
                context.startActivity(
                    Intent(context, RaiganjActivity::class.java)
                )
            }, colors = buttonColors(backgroundColor = Teal200)) {
                Text("Raiganj")
            }
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
//                Text(text = "$targetCount")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center) {
            var count by remember { mutableStateOf(0) }
            Button(onClick = {
                context.startActivity(
                    Intent(context, KaliyaganjActivity::class.java)
                )
            }, colors = buttonColors(backgroundColor = Teal200)) {
                Text("Kaliyaganj")
            }
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
//                Text(text = "$targetCount")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center) {
            var count by remember { mutableStateOf(0) }
            Button(onClick = {
                context.startActivity(
                    Intent(context, RadhikapurActivity::class.java)
                )
            }, colors = buttonColors(backgroundColor = Teal200)) {
                Text("Radhikapur")
            }
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
//                Text(text = "$targetCount")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center) {
            var count by remember { mutableStateOf(0) }
            Button(onClick = {
                context.startActivity(
                    Intent(context, IndustryActivity::class.java)
                )
            }, colors = buttonColors(backgroundColor = Teal200)) {
                Text("Industry")
            }
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        slideInVertically { height -> height } + fadeIn() with
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        slideInVertically { height -> -height } + fadeIn() with
                                slideOutVertically { height -> height } + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = false)
                    )
                }
            ) { targetCount ->
//                Text(text = "$targetCount")
            }
        }

        var expanded by remember { mutableStateOf(false) }
        Surface(
            color = Teal200,
            onClick = { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }
            ) { targetExpanded ->
                if (targetExpanded) {
                    Text(text = " According to the West Bengal Land Reforms Act, one can buy a maximum of 24.5 acres of rainfed land and 17.5 acres of irrigated land",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                } else {
                    Icon(Icons.Filled.Info, contentDescription = null)
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BhumiTheme {
        About()
    }
}