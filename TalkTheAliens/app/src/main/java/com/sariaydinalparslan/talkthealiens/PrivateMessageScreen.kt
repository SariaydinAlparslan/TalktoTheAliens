package com.sariaydinalparslan.talkthealiens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sariaydinalparslan.talkthealiens.viewmodel.MessageViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun PrivateMessageScreen(navController: NavController) {
    val viewModel: MessageViewModel = viewModel()
    val messageList = viewModel.messagelist.observeAsState(listOf())
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val liste = listOf("did you think there were you earthlings looking for a new life to live on","the universe is a big place. you are not alone","hello earthling"," we're friends","WE ARE LOST","there is life on other planets","FREQUENCY",
        "HELLO","we're coming to kill you","sir, now is the time to attack","WE NEED HELP","is there a god","the planet that sent this message was cast 1000 light years ago","come to the ton618 black hole","einstein was right","parallel universe right",
        "you shouldn't have killed Galileo Galilei","tomorrow will be very bad","the meteorite will hit you in 30 years and 20 days. be careful","the meteorite will hit you in 1 year and 10 days be careful","don't believe in fortune telling","the sun has 10 years left to live")
    val randommessage = remember { mutableStateOf(0)}
    LaunchedEffect(key1 = true){
        randommessage.value=Random.nextInt(17)
        Log.e("alp","${randommessage.value.toInt()}")
    }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Gray
                ,title={
                    Box {
                        Row(modifier = Modifier) {
                            Image(painter = painterResource(id = R.drawable.icon1), contentDescription ="message" , modifier = Modifier.padding(top = 5.dp))
                            Text(text = "Direct Messages", modifier = Modifier.padding(start = 20.dp, top = 9.dp), fontSize = 15.sp, color = Color.Black)
                            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Text(text = "Press the Alien's Message ", color = Color.Black, fontSize = 12.sp, modifier = Modifier.padding(start = 18.dp, top = 4.dp) )
                                Text(text = "to Translate", color = Color.Black, fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp, top = 2.dp) )
                                Image(painter = painterResource(id = R.drawable.voice100), contentDescription ="voice", modifier = Modifier.padding(bottom = 6.dp) )
                            }
                        }
                    }
                },
                contentColor =  Color.White, actions = {
                }
            )
        },
        content = {
            Box(modifier = Modifier) {
                WelcomeBackground()
                LazyColumn(state=state,modifier = Modifier.background(color = Color.Transparent)) {
                    items(
                            count = messageList.value!!.count(),
                            itemContent = {
                                val tekmessage = messageList.value!![it]

                                Box(modifier = Modifier) {
                                    Column(modifier = Modifier) {
                                        Spacer(modifier = Modifier.padding(top = 5.dp))
                                        Card(modifier = Modifier
                                            .background(
                                                brush = Brush.horizontalGradient(
                                                    listOf(
                                                        colorResource(id = R.color.message1),
                                                        colorResource(id = R.color.message2)
                                                    )
                                                )
                                            )
                                            .fillMaxWidth()
                                            .clip(CircleShape),
                                            shape = RoundedCornerShape(10.dp),
                                            elevation = 10.dp
                                        ){
                                            Column {
                                                Text(
                                                    text = tekmessage.gidenmesaj.toString(),
                                                    modifier = Modifier
                                                        .background(
                                                            brush = Brush.horizontalGradient(
                                                                listOf(
                                                                    colorResource(id = R.color.message1),
                                                                    colorResource(id = R.color.message2)
                                                                )
                                                            )
                                                        )
                                                        .fillMaxWidth()
                                                        .padding(end = 10.dp), fontSize = 15.sp, color = Color.Black
                                                    ,textAlign = TextAlign.End,
                                                )
                                                Text(
                                                    text = tekmessage.whichplanet.toString(),
                                                    modifier = Modifier
                                                        .background(
                                                            brush = Brush.horizontalGradient(
                                                                listOf(
                                                                    colorResource(id = R.color.message1),
                                                                    colorResource(id = R.color.message2)
                                                                )
                                                            )
                                                        )
                                                        .fillMaxWidth()
                                                        .padding(end = 10.dp), fontSize = 10.sp, color = Color.Black
                                                    ,textAlign = TextAlign.End,
                                                )
                                            }

                                        }
                                        Card(modifier = Modifier
                                            .background(color = Color.Transparent)
                                            .fillMaxWidth(), shape = RoundedCornerShape(10.dp),
                                            elevation = 10.dp,
                                        ){
                                            Column(modifier = Modifier) {
                                                Text(
                                                    textAlign = TextAlign.Start,
                                                    text = tekmessage.gelenmesaj.toString(),color = Color.Black,fontSize = 15.sp,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(start = 10.dp)
                                                        .clickable {
                                                            if (tekmessage.gelenmesaj.equals("")) {

                                                            } else {
                                                                viewModel.realMessage(liste[randommessage.value])
                                                                viewModel.textToSpeech(context)
                                                            }

                                                        }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        )
                }


            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), Alignment.BottomCenter){
                FloatingActionButton(onClick = { 
                    coroutineScope.launch { state.animateScrollToItem(
                    messageList.value.size) }
                }) {
                    Icon(painter= painterResource(id = R.drawable.arrow_24), contentDescription = "")

                }
            }
        }
    )

}
@Composable
private fun WelcomeBackground() {
    Image(
        painterResource(id = R.drawable.zircon), contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()

    )
}


