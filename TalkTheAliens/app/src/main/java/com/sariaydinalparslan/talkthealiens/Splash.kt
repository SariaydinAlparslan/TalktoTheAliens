package com.sariaydinalparslan.talkthealiens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay


@Composable
fun Splash(navController:NavController) {
    val scale = remember { Animatable(0f) }
    val lottieFile by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.space))
    val progress by animateLottieCompositionAsState(
        composition = lottieFile,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false,
        isPlaying = true
    )
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("sign_in")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White), Alignment.Center) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(composition = lottieFile, progress = progress, contentScale = ContentScale.Fit, modifier = Modifier.size(400.dp))
            Image(
                painter = painterResource(id = R.drawable.log2), contentDescription = "SplashScreen",
                modifier = Modifier
                    .width(1500.dp)
                    .height(1500.dp)
                    .scale(scale.value)
                    .background(color = Color.Transparent)
            )
        }

    }
}
