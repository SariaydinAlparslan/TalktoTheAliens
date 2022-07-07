package com.sariaydinalparslan.talkthealiens.planets

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.sariaydinalparslan.talkthealiens.Entity.Messages
import com.sariaydinalparslan.talkthealiens.R
import java.util.*

@ExperimentalMaterialApi
@Composable
fun Jupiter(navController: NavController){
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    intent.putExtra(
        RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
    )
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Something")
    val context = LocalContext.current
    val pendIntent = PendingIntent.getActivity(context, 0, intent, 0)
    var code by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) {
        if (it.resultCode != Activity.RESULT_OK) {
            return@rememberLauncherForActivityResult
        }
        val result = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

        if (result !== null) {
            code = result[0] ?: ""
        }
    }
    val lottieFile0 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.jupiter3))
    val progress0 by animateLottieCompositionAsState(
        composition = lottieFile0,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false,
        isPlaying = true
    )
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(
                title = {},
                backgroundColor = Color.Transparent
            )
        },
        backLayerContent = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ){
                Box(modifier = Modifier){
                    WelcomeBackground()
                    Column(modifier = Modifier) {
                        Box(modifier = Modifier){
                            LottieAnimation(composition = lottieFile0,
                                progress = progress0,
                                alignment = Alignment.TopStart,
                                modifier = Modifier
                                    .size(400.dp)
                                    .clickable {
                                        launcher.launch(
                                            IntentSenderRequest
                                                .Builder(pendIntent)
                                                .build()
                                        )
                                    })
                        }
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(
                            text = "Press The Planet And Say Something",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = code,
                            style = MaterialTheme.typography.h6,fontSize =25.sp,fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = {if(code.isEmpty()){
                            Toast.makeText(context, "PLEASE PRESS THE PLANET AND SAY SOMETHING", Toast.LENGTH_SHORT).show()
                        }else if (code.isNotEmpty()) {
                            bildirimOlustur1(context, notificationcode = code)
                            val db = FirebaseDatabase.getInstance()
                            val user = Firebase.auth.currentUser
                            val refMessages=db.getReference("message${user?.uid.toString()}")
                            val newmessage = Messages("",code,user?.email.toString(),"Jupiter")
                            refMessages.push().setValue(newmessage)
                            navController.navigate("back")
                        }},
                            Modifier
                                .size(350.dp)
                                .padding(start = 70.dp, top = 15.dp, bottom = 16.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors =
                                        listOf(
                                            colorResource(id = R.color.alp),
                                            colorResource(id = R.color.a)
                                        )
                                    )
                                ), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                            Text(text = "SEND MESSAGE", color = Color.Black)
                        }
                    }
                }
            }
        },
        frontLayerContent = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent
            ){
                Box(modifier = Modifier){
                    WelcomeBackground2()
                    Column(modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "INFO", modifier = Modifier
                            .background(color = Color.Transparent))
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(text = "JUPITER IS THE LARGEST PLANET IN THE SOLAR SYSTEM. IT IS APPROXIMATELY 143,000 KILOMETERS (ABOUT 89,000 MILES) WIDE AT ITS EQUATOR."
                            ,modifier = Modifier
                                .padding(start = 35.dp)
                                .background(color = Color.Transparent), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "JUPITER IS SO LARGE THAT ALL OF THE OTHER PLANETS IN THE SOLAR SYSTEM COULD FIT INSIDE IT. MORE THAN 1,300 EARTHS WOULD FIT INSIDE JUPITER."
                            ,modifier = Modifier
                                .padding(start = 35.dp)
                                .background(color = Color.Transparent), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text =  "JUPITER IS LIKE A STAR IN COMPOSITION. IF JUPITER HAD BEEN ABOUT 80 TIMES MORE MASSIVE, IT WOULD HAVE BECOME A STAR RATHER THAN A PLANET."
                            ,modifier = Modifier
                                .padding(start = 35.dp)
                                .background(color = Color.Transparent), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text =  "JUPITER IS THE FIFTH PLANET FROM THE SUN. JUPITER'S AVERAGE DISTANCE FROM THE SUN IS 5.2 ASTRONOMICAL UNITS, OR AU."
                            ,modifier = Modifier
                                .padding(start = 35.dp)
                                .background(color = Color.Transparent), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text =  "THIS DISTANCE IS A LITTLE MORE THAN FIVE TIMES THE DISTANCE FROM EARTH TO THE SUN. WHEN VIEWED FROM EARTH, JUPITER IS USUALLY THE SECOND BRIGHTEST PLANET IN THE NIGHT SKY, AFTER VENUS. THE PLANET IS NAMED AFTER JUPITER, THE KING OF THE ROMAN GODS IN MYTHOLOGY."
                            ,modifier = Modifier
                                .padding(start = 35.dp)
                                .background(color = Color.Transparent), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.padding(15.dp))
                        Text(text = "SCROLL DOWN THE SCREEN", modifier = Modifier
                            .background(color = Color.Transparent), textAlign = TextAlign.Center, fontSize = 35.sp)
                    }
                }
            }
        }
    )
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
private fun WelcomeBackground2() {
    Image(
        painterResource(id = R.drawable.zircon), contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}

fun bildirimOlustur1(context: Context, notificationcode :String){
    val builder: NotificationCompat.Builder
    val bildirimYoneticisi = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val kanalId = "kanalId"
        val kanalAd = "kanalAd"
        val kanalTanitim = "kanalTanitim"
        val kanalOnceligi = NotificationManager.IMPORTANCE_HIGH
        var kanal: NotificationChannel? = bildirimYoneticisi.getNotificationChannel(kanalId)

        if (kanal == null){
            kanal = NotificationChannel(kanalId,kanalAd,kanalOnceligi)
            kanal.description = kanalTanitim
            bildirimYoneticisi.createNotificationChannel(kanal)
        }

        builder = NotificationCompat.Builder(context,kanalId)
        builder.setContentTitle("Direct Message")
            .setContentText("$notificationcode")
            .setSmallIcon(R.drawable.circle_24)
            .setAutoCancel(true)


    }else{
        builder = NotificationCompat.Builder(context)

        builder.setContentTitle("Direct Message")
            .setContentText("$notificationcode")
            .setSmallIcon(R.drawable.circle_24)
            .setAutoCancel(true)
            .priority = Notification.PRIORITY_HIGH
    }

    bildirimYoneticisi.notify(1,builder.build())
}
