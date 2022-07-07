package com.sariaydinalparslan.talkthealiens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Signin(navController: NavController) {



    val image =  painterResource(id = R.drawable.giris)

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val context = LocalContext.current

    val auth = Firebase.auth

    LaunchedEffect(key1 = true,) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navController.navigate("permission")
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        WelcomeBackground()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {
            Image(painter = image, contentDescription = "background" )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(value = emailValue.value, onValueChange = { emailValue.value = it }, modifier = Modifier.fillMaxWidth(0.8f), label = { Text(text = "Email") })
                    Spacer(modifier =Modifier.padding(10.dp))
                    OutlinedTextField(value = passwordValue.value, onValueChange = { passwordValue.value = it },label = { Text(text = "Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester))
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                        if(passwordValue.value.equals("")||emailValue.value.equals("")){
                            Toast.makeText(context, "Email or Password is Empty", Toast.LENGTH_SHORT).show()
                        }else{
                            auth.signInWithEmailAndPassword(emailValue.value.trim(), passwordValue.value.trim())
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        auth.currentUser
                                        navController.navigate("permission")
                                        Log.e("ss", "ok")
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "WRONG PASSWORD OR USERNAME",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                                     },modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    colorResource(id = R.color.a), colorResource(id = R.color.alp)
                                )
                            )
                        ), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                            Text(text = "SIGN IN")
                        }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Create An Account",
                        modifier = Modifier.clickable(onClick = {
                            navController.navigate("sign_up")
                        })
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }

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















