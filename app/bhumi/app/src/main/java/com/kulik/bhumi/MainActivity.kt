package com.kulik.bhumi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kulik.bhumi.ui.theme.BhumiTheme
import com.kulik.bhumi.ui.theme.Black
import com.kulik.bhumi.ui.theme.Teal200
import com.kulik.bhumi.ui.theme.Yellow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

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
                    Home()
                }
            }
        }
    }
}

class Mouza(name: String, code: String, sheets: List<String>) {
    val name = name
    val code = code
    val sheets = sheets
}

/*
* https://twitter.com/RodrigoMartinD/status/1577719043720626178?s=20&t=5w19yA6WVrPaXI4-fIs-PA
*
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MouzaCards() {
    val paddingModifier = Modifier.padding(5.dp)
    val listData = listOf<Mouza>(
        Mouza("madhupur_109", "109", sheets = listOf<String>("0")),
        Mouza("basudebpur_115", "115", sheets = listOf<String>("0")),
        Mouza("bhoria_118", "118", sheets = listOf<String>("0")),
        Mouza("marikura_144", "144", sheets = listOf<String>("0")),
        Mouza("rupahar_202", "202", sheets = listOf<String>("0")),
        Mouza("sankarpur_113", "113", sheets = listOf<String>("0")),
        Mouza("ekore_110", "110", sheets = listOf<String>("1", "2")),
        Mouza("raiganj_150", "150", sheets = listOf<String>("01", "02", "03")),
        Mouza("tenohari_143", "143", sheets = listOf<String>("1", "2")),
    )
    val context = LocalContext.current

    val openDialog = remember { mutableStateOf(false) }
    var alerttext by remember { mutableStateOf("") }
    var dialogMouza by remember {
        mutableStateOf(Mouza("", "", listOf()))
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Select a sheet out of these ${dialogMouza.sheets.size}")
            },
            text = {
                LazyVerticalGrid(cells = GridCells.Fixed(4),) {
                    items(dialogMouza.sheets) {
                        Card(shape = RoundedCornerShape(20.dp),
                            elevation = 10.dp,
                            backgroundColor = Yellow,
                            modifier = paddingModifier.clickable() {
                                context.startActivity(Intent(context, MapActivity::class.java).putExtra("url", "https://agrohikulik.web.app/raiganj_06/${dialogMouza.name}/${it}/MouzaMap.html"))
                                openDialog.value = false
                            }) {
                            Text(text = it,
                                style = TextStyle( color = Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center),
                                modifier = Modifier.padding(12.dp))

                        }
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }

    LazyVerticalGrid(cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(listData) {
            Card(shape = RoundedCornerShape(20.dp),
                elevation = 10.dp,
                backgroundColor = Yellow,
                modifier = paddingModifier.clickable() {
                    if (it.sheets.size > 1) {
                        openDialog.value = true
                        dialogMouza = it
                    } else {
                        context.startActivity(Intent(context, MapActivity::class.java).putExtra("url", "https://agrohikulik.web.app/raiganj_06/${it.name}/MouzaMap.html"))
                    }
            }) {
                Text(text = it.name,
                    style = TextStyle( color = Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center),
                    modifier = Modifier.padding(12.dp))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MouzaCards2() {
    val paddingModifier = Modifier.padding(5.dp)
    val listData = listOf<Mouza>(
        Mouza("dhamja_137", "109", sheets = listOf<String>("0")),
        Mouza("chirail_102", "115", sheets = listOf<String>("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")),
        Mouza("dhankoil_84", "118", sheets = listOf<String>("1", "2")),
        Mouza("rashidpur_85", "144", sheets = listOf<String>("0")),
        Mouza("mahadebpur_99", "202", sheets = listOf<String>("0")),
        Mouza("ratun_123", "113", sheets = listOf<String>("0")),
        Mouza("bhabanipur_75", "110", sheets = listOf<String>("1", "2")),
        Mouza("araji_joydebpur_76", "150", sheets = listOf<String>("0")),
        Mouza("laxmipur_27", "143", sheets = listOf<String>("0")),
        Mouza("uttar_gouripur_64", "143", sheets = listOf<String>("0")),
        Mouza("bochadanga_131", "143", sheets = listOf<String>("0")),
        Mouza("doulatbati_26", "143", sheets = listOf<String>("0")),
        Mouza("dalimgaon_31", "143", sheets = listOf<String>("1", "2")),
        Mouza("chaklaxmi_32", "143", sheets = listOf<String>("0")),
        Mouza("radhikapur_57", "143", sheets = listOf<String>("0")),
        Mouza("uttar_krishnapur_55", "143", sheets = listOf<String>("1", "2")),
        Mouza("gotgaon_56", "143", sheets = listOf<String>("0")),
        Mouza("maheshpur_78", "143", sheets = listOf<String>("1", "2")),
        Mouza("fatepur_135", "143", sheets = listOf<String>("1", "2")),
        Mouza("haldibari_96", "143", sheets = listOf<String>("0")),
        Mouza("hariharpur_86", "143", sheets = listOf<String>("0")),
        Mouza("mirjapur_1", "143", sheets = listOf<String>("0")),
        Mouza("uttar_durgapur_04", "143", sheets = listOf<String>("0")),
        Mouza("kathandari_05", "143", sheets = listOf<String>("0")),
        Mouza("bhurkutpara_06", "143", sheets = listOf<String>("0")),
        Mouza("mirabati_07", "143", sheets = listOf<String>("0")),
        Mouza("uttar_gouripur_08", "143", sheets = listOf<String>("0")),
        Mouza("pachakandar_11", "143", sheets = listOf<String>("0")),
        Mouza("buridangi_13", "143", sheets = listOf<String>("0")),
        Mouza("batasan_14", "143", sheets = listOf<String>("0")),
        Mouza("shankarpur_16", "143", sheets = listOf<String>("0")),
        Mouza("uttar_laxmipur_17", "143", sheets = listOf<String>("0")),
        Mouza("velai_20", "143", sheets = listOf<String>("0")),
        Mouza("majlispur_104", "143", sheets = listOf<String>("0")),
        Mouza("keotan_101", "143", sheets = listOf<String>("0")),
        Mouza("lohatara_30", "143", sheets = listOf<String>("0")),


    )
    val context = LocalContext.current

    val openDialog = remember { mutableStateOf(false) }
    var dialogMouza by remember {
        mutableStateOf(Mouza("", "", listOf()))
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Select a sheet out of these ${dialogMouza.sheets.size}")
            },
            text = {
                LazyVerticalGrid(cells = GridCells.Fixed(4),) {
                    items(dialogMouza.sheets) {
                        Card(shape = RoundedCornerShape(20.dp),
                            elevation = 10.dp,
                            backgroundColor = Yellow,
                            modifier = paddingModifier.clickable() {
                                context.startActivity(Intent(context, MapActivity::class.java).putExtra("url", "https://agrohikulik.web.app/kaliyaganj_18/${dialogMouza.name}/${it}/MouzaMap.html"))
                                openDialog.value = false
                            }) {
                            Text(text = it,
                                style = TextStyle( color = Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center),
                                modifier = Modifier.padding(12.dp))

                        }
                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }

    LazyVerticalGrid(cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(listData) {
            Card(shape = RoundedCornerShape(20.dp),
                elevation = 10.dp,
                backgroundColor = Yellow,
                modifier = paddingModifier.clickable() {
                    if (it.sheets.size > 1) {
                        openDialog.value = true
                        dialogMouza = it
                    } else {
                        context.startActivity(Intent(context, MapActivity::class.java).putExtra("url", "https://agrohikulik.web.app/kaliyaganj_18/${it.name}/MouzaMap.html"))
                    }
                }) {
                Text(text = it.name,
                    style = TextStyle( color = Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center),
                    modifier = Modifier.padding(12.dp))
            }
        }
    }
}


/*
* blog
* https://www.valueof.io/blog/animation-compose-api-summary
*
 */

@Composable
fun LargeCard(){
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
                Text("Hello",
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp))
            }
        }
    }
}


/*
 * code from
 * https://github.com/yaoxiawen/ComposeAnimationDemo/blob/master/app/src/main/java/com/example/composeanimationdemo/Demo1.kt
*/
fun Modifier.swipeToDismiss(onDismissed: () -> Unit): Modifier = composed {
    composed {
        //水平位移
        val offsetX = remember {
            androidx.compose.animation.core.Animatable(0f)
        }
        //使用 pointerInput 修饰符，可以获取对传入指针触摸事件的低级别访问，并跟踪用户使用同一指针拖动的速度。
        pointerInput(Unit) {
            //衰减动画，用于计算滑动动画的最终停止位置
            val decay = splineBasedDecay<Float>(this)
            //开启协程监听触摸事件
            coroutineScope {
                //使用while (true)一直监听触摸事件
                while (true) {
                    //等待触摸按下事件
                    //awaitPointerEventScope：挂起并安装指针输入块，该块可以等待输入事件并立即响应它们
                    //awaitFirstDown：第一个down事件
                    val pointerId = awaitPointerEventScope {
                        awaitFirstDown().id
                    }
                    //如果动画当前正在运行，我们应将其拦截。可以通过对 Animatable 调用 stop 来实现此目的。
                    // 请注意，如果动画未运行，系统会忽略该调用。VelocityTracker 用于计算用户从左向右移动的速度。
                    offsetX.stop()
                    val velocityTracker = VelocityTracker()
                    //等待拖动事件
                    awaitPointerEventScope {
                        //监听水平滑动
                        horizontalDrag(pointerId) { change ->
                            val horizontalDragOffset = offsetX.value + change.positionChange().x
                            //只能在另一个 launch 代码块内发起对 snapTo 的调用，
                            // 因为 awaitPointerEventScope 和 horizontalDrag 是受限的协程范围。
                            // 也就是说，它们只能对 awaitPointerEvents 执行 suspend，而 snapTo 并不是指针事件。
                            launch {
                                offsetX.snapTo(horizontalDragOffset)
                            }
                            //记录下时间和位置就可以计算出速度
                            velocityTracker.addPosition(change.uptimeMillis, change.position)
                            //消费掉手势事件，而不是传递给外部
                            change.consumePositionChange()
                        }
                    }
                    //滑动事件结束，计算滑动速度
                    val velocity = velocityTracker.calculateVelocity().x
                    //计算最终停止位置
                    val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                    //给动画设置边界
                    offsetX.updateBounds(
                        lowerBound = -size.width.toFloat(),
                        upperBound = size.width.toFloat()
                    )
                    launch {
                        if (targetOffsetX.absoluteValue <= size.width) {
                            //返回原位
                            offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                        } else {
                            //使用衰减动画直到滑动边界
                            offsetX.animateDecay(velocity, decay)
                            onDismissed()
                        }
                    }
                }
            }
        }
            //应用水平位移给元素
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
    }
}


@Composable
fun Home() {
    val shape = CircleShape
    var enabled = true
    val context = LocalContext.current

    Column (verticalArrangement= Arrangement.SpaceBetween) {
        Text(
            text = "Raiganj map",
            style = TextStyle( color = Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Black, shape)
                .background(Teal200, shape)
                .padding(16.dp)
                .clickable(enabled = enabled) {
                    enabled = false
                    // onCLick()
                    context.startActivity(
                        Intent(context, LandActivity::class.java).putExtra(
                            "url",
                            "https://agrohikulik.web.app/raiganj_06/basudebpur_115/MouzaMap.html"
                        )
                    )
                },
        )

        MouzaCards()

        Divider(Modifier.background(Teal200), thickness = 1.dp)

        MouzaCards2()

        Text(text = " According to the West Bengal Land Reforms Act, one can buy a maximum of 24.5 acres of rainfed land and 17.5 acres of irrigated land",
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BhumiTheme {
        Home()
    }
}