package com.sariaydinalparslan.talkthealiens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.sariaydinalparslan.talkthealiens.ui.theme.museumTypee

@ExperimentalPermissionsApi
@Composable
fun HomeScreen(navController: NavController){
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ){
        val context = LocalContext.current
        val recordPermissionState = rememberPermissionState(android.Manifest.permission.RECORD_AUDIO)

        PermissionRequired(
            permissionState = recordPermissionState,
            permissionNotGrantedContent = {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.primary)) {
                    WelcomeBackground()
                    Box(modifier = Modifier, contentAlignment = Alignment.Center){
                        Column(
                            modifier = Modifier.padding(top = 250.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(55.dp),
                                elevation = 15.dp
                            ) {
                                Box(modifier = Modifier
                                    .size(150.dp, 150.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.permission),
                                        contentDescription = "Space",
                                        contentScale = ContentScale.Crop
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Transparent)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(20.dp))
                            Box(modifier = Modifier) {
                                Button(
                                    onClick = {
                                        recordPermissionState.launchPermissionRequest()
                                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White,contentColor = Color.Black),
                                    shape = RoundedCornerShape(25.dp)
                                ) {
                                    Text(
                                        text = "Record Audio Permission",
                                        fontSize = 25.sp,
                                        fontFamily = museumTypee,
                                        color = Color.DarkGray,
                                    )
                                }
                            }
                        }
                    }

                }
            },
            permissionNotAvailableContent = {
                Column(
                    Modifier.fillMaxWidth(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally
                ){
                    Text("Record Permission Denied.", fontSize = 22.sp, color = Color.Red)
                    Spacer(Modifier.height(10.dp))
                    Text("Open App Setting & Record Audio Permission.", fontSize = 16.sp)
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intent.data = Uri.parse("package:${context.packageName}")
                            context.startActivity(intent)
                        }
                    ){
                        Text("Open App Setting")
                    }
                }
            },
            content = {
                Box(modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.primary)) {
                        WelcomeBackground()
                        Box(modifier = Modifier, contentAlignment = Alignment.Center){
                            Column(
                                modifier = Modifier.padding(top = 250.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(55.dp),
                                    elevation = 15.dp
                                ) {
                                    Box(modifier = Modifier
                                        .size(150.dp, 150.dp)
                                       ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.permission2),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color.Transparent)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(20.dp))
                                Box(modifier = Modifier) {
                                    Button(
                                        onClick = {
                                            navController.navigate("sayfa1")
                                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White,contentColor = Color.Black),
                                        shape = RoundedCornerShape(25.dp)
                                    ) {
                                        Text(
                                            text = "Start",
                                            fontSize = 25.sp,
                                            fontFamily = museumTypee,
                                            color = Color.DarkGray,
                                        )
                                    }
                                }
                            }
                        }

                    }

            }
        )
    }
}
@Composable
private fun WelcomeBackground() {
    Image(
        painterResource(id = R.drawable.zircon), contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()

    )
}

