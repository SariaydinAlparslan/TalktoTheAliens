package com.sariaydinalparslan.talkthealiens


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sariaydinalparslan.talkthealiens.viewmodel.CommunityViewModel
import kotlinx.coroutines.launch




@Composable
fun CommunityMessageScreen(navController: NavController) {
    val viewModel: CommunityViewModel = viewModel()
    val communitymessageList = viewModel.communitymessagelist.observeAsState(listOf())
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent
                ,title={
                    Box {
                        Row(modifier = Modifier) {
                            Image(painter = painterResource(id = R.drawable.chat24), contentDescription ="message" , modifier = Modifier.padding(top = 5.dp))
                            Text(text = "Community", modifier = Modifier.padding(start = 30.dp, top = 4.dp), fontSize = 25.sp)
                        }
                    }
                },
                contentColor =  Color.Transparent
            )
        },
        content = {
            Box(modifier = Modifier) {
                WelcomeBackground()
                LazyColumn(state=state,modifier = Modifier.background( brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.message1),
                        colorResource(id = R.color.message2)
                    )
                ))) {
                    items(
                        count = communitymessageList.value!!.count(),
                        itemContent = {
                            val tekmessage = communitymessageList.value!![it]
                            Box(modifier = Modifier) {
                                Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
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
                                                .padding(end = 5.dp), fontSize = 15.sp, color = Color.Black
                                            ,textAlign = TextAlign.End,
                                        )
                                    }
                                    Card(modifier = Modifier
                                        .background(color = Color.Transparent)
                                        .fillMaxWidth(), shape = RoundedCornerShape(10.dp),
                                        elevation = 10.dp,
                                        ){
                                        Text(
                                        textAlign = TextAlign.End,
                                        text = tekmessage.kullaniciadi.toString(),color = Color.Black,
                                        modifier = Modifier.padding(end = 15.dp), fontSize = 12.sp
                                    )
                                    }
                                }

                            }
                        }
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp), Alignment.BottomStart){
                FloatingActionButton(onClick = {
                    coroutineScope.launch { state.animateScrollToItem(
                        communitymessageList.value.size) }
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




