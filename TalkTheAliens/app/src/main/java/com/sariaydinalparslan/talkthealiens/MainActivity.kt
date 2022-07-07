package com.sariaydinalparslan.talkthealiens

import Neptune
import android.os.Bundle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sariaydinalparslan.talkthealiens.planets.*
import com.sariaydinalparslan.talkthealiens.ui.theme.TalkTheAliensTheme
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TalkTheAliensTheme {
                SayfaGeçişleri()
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun SayfaGeçişleri() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            Splash(navController = navController)
        }
        composable("sayfa1"){
            Sayfa1(navController = navController)
        }
        composable("sun"){
            Sun(navController = navController)
        }
        composable("moon"){
            Moon(navController = navController)
        }
        composable("earth"){
            Earth(navController = navController)
        }
        composable("venus"){
            Venus(navController = navController)
        }
        composable("neptun"){
            Neptune(navController = navController)
        }
        composable("mercury"){
            Mercury(navController = navController)
        }
        composable("saturn"){
            Saturn(navController = navController)
        }
        composable("mars"){
            Mars(navController = navController)
        }
        composable("jupiter"){
            Jupiter(navController = navController)
        }
        composable("back"){
            Sayfa1(navController = navController)
        }
        composable("alien_message"){
            PrivateMessageScreen(navController = navController)
        }
        composable("community"){
            CommunityMessageScreen(navController = navController)
        }
        composable("sign_in"){
            Signin(navController = navController)
        }
        composable("sign_up"){
            RegisterPage(navController = navController)
        }
        composable("permission"){
            HomeScreen(navController = navController)
        }
    }
}
@Composable
fun Sayfa1(navController: NavController){

    FirebaseMessaging.getInstance().token
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
        })
    Box(modifier = Modifier.fillMaxSize()){
        val items = listOf(
            CircleCard(navController,""),
            CircleCard(navController,""),
            CircleCard(navController,""),
            CircleCard(navController,""),
            CircleCard(navController,""),
            CircleCard(navController,""),
            CircleCard(navController,""),
            CircleCard(navController,"")
      )
            Scaffold(
                topBar = {
                    TopAppBar(
                        title={
                            Row (modifier = Modifier){
                                Text(text = "CHOOSE A PLANET", fontSize = 15.sp, modifier = Modifier.padding(4.dp))
                                Row(modifier = Modifier
                                    .padding(start = 8.dp)
                                    .clickable { navController.navigate("community") }){
                                    Icon(painter = painterResource(id = R.drawable.community24), modifier = Modifier.size(25.dp) , contentDescription = "")
                                    Spacer(modifier = Modifier.padding(start =5.dp))
                                    Text(text = "Community", fontSize = 10.sp, modifier = Modifier.padding(top = 5.dp))
                                    }
                                Row(modifier = Modifier
                                    .padding(start = 25.dp)
                                    .clickable { navController.navigate("alien_message") }) {
                                    Icon(painter = painterResource(id = R.drawable.send24),modifier = Modifier.size(25.dp), contentDescription = "")
                                    Spacer(modifier = Modifier.padding(start =5.dp))
                                    Text(text = "Direct Messages",fontSize = 10.sp,modifier = Modifier.padding(top = 5.dp))
                                    }
                            }
                        }, backgroundColor = Color.Black,
                        contentColor = Color.White
                    )
                },
                content = {  Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        WelcomeBackground()
                        CurvedScrollItem(items.size) { index ->
                            Column(
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Image(painter = painterResource(id =
                                when (index) {
                                    0 -> R.drawable.mercury
                                    1 -> R.drawable.venus
                                    2 -> R.drawable.earth
                                    3 -> R.drawable.moon2
                                    4 -> R.drawable.mars5
                                    5 -> R.drawable.jupiter
                                    6 -> R.drawable.neptun
                                    7 -> R.drawable.saturn4
                                    else -> R.drawable.venus
                                }
                                ),
                                    contentDescription = "Curved Image",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            when (index) {
                                                0 -> navController.navigate("mercury")
                                                1 -> navController.navigate("venus")
                                                2 -> navController.navigate("earth")
                                                3 -> navController.navigate("moon")
                                                4 -> navController.navigate("mars")
                                                5 -> navController.navigate("jupiter")
                                                6 -> navController.navigate("neptun")
                                                7 -> navController.navigate("saturn")
                                                else -> {}
                                            }
                                        }
                                )
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(
                                    text = when (index) {
                                        0 -> "MERCURY"
                                        1 -> "VENUS"
                                        2 -> "EARTH(FOR COMMUNİTY)"
                                        3 -> "MOON"
                                        4 -> "MARS"
                                        5 -> "JUPITER"
                                        6 -> "NEPTUN"
                                        7 -> "SATURN"
                                        else -> ""
                                    }
                                    , color = Color.White, modifier = Modifier.padding(start = 30.dp))
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.sun2),
                            contentDescription = "Sun",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .width(195.dp)
                                .height(195.dp)
                                .clip(RoundedCornerShape(120.dp))
                                .padding(start = 10.dp)
                                .background(color = Color.Transparent)
                                .clickable {
                                    navController.navigate("sun")
                                }
                            )
                        }
                    }
                }
            )
  }
}
@Composable
fun CircleCard(navController: NavController,text : String){
    Card(modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(55.dp)
    ) {
    }
}
@Composable
private fun WelcomeBackground() {
    Image(
        painterResource(id = R.drawable.ar), contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}
@Composable
fun CurvedScrollItem(
    itemCount: Int,
    item: @Composable (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    var size = remember { mutableStateOf(IntSize.Zero) }
    val scope = rememberCoroutineScope()
    val indices = remember { IntArray(itemCount) { 0 } }

    val flingBehaviour = object : FlingBehavior {
        override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
            val value = scrollState.value
            indices.minByOrNull { Math.abs(it - value) }?.let {
                scope.launch {
                    scrollState.animateScrollTo(it)
                }
            }
            return initialVelocity
        }
    }

    Box(
        modifier = Modifier
            .onSizeChanged {
                size.value = it
            }
    ) {
        Layout(
            content = {
                repeat(itemCount) {
                    item(it)
                }
            },
            modifier = Modifier.verticalScroll(
                scrollState, flingBehavior = flingBehaviour
            )
        ) { measurables, constraints ->
            val itemSpacing = 16.dp.roundToPx()
            var contentHeight = (itemCount - 1) * itemSpacing

            val placeables = measurables.mapIndexed { index, measurable ->
                val placeable = measurable.measure(constraints = constraints)
                contentHeight += if (index == 0 || index == measurables.lastIndex) {
                    placeable.height / 2
                } else {
                    placeable.height
                }
                placeable
            }

            layout(constraints.maxWidth, size.value.height + contentHeight) {
                val startOffset = size.value.height / 2 - placeables[0].height / 2
                var yPosition = startOffset

                val scrollPercent = scrollState.value.toFloat() / scrollState.maxValue

                placeables.forEachIndexed { index, placeable ->
                    val elementRatio = index.toFloat() / placeables.lastIndex
                    val interpolatedValue = Math.cos((scrollPercent - elementRatio) * Math.PI)
                    val indent = interpolatedValue * size.value.width / 2

                    placeable.placeRelativeWithLayer(x = indent.toInt(), y = yPosition) {
                        alpha = interpolatedValue.toFloat()
                    }
                    indices[index] = yPosition - startOffset
                    yPosition += placeable.height + itemSpacing
                }
            }
        }
    }
}





