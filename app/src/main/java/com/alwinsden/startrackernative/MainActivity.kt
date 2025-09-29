package com.alwinsden.startrackernative

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alwinsden.startrackernative.network.healthCheckStatus
import com.alwinsden.startrackernative.ui.theme.StarTrackerNativeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            )
        )
        setContent {
            StarTrackerNativeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFFFFFFFF)
                        )
                ) { innerPadding ->
                    LoginCentral()
                }
            }
        }
    }
}

@Composable
fun LoginCentral() {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) { EntryPoint() }

    }
}

class EntryPointViewModel : ViewModel() {
    var userName by mutableStateOf(TextFieldState(""))
    var userPassword by mutableStateOf(TextFieldState(""))
    var healthCheckStatus by mutableStateOf("PENDING")
    var loaderState by mutableStateOf(false)

    fun user_authentication() {
        println(userName.text)
        println(userPassword.text)
    }

    fun healthCheck() {
        viewModelScope.launch {
            loaderState = true
            val statusResponse = healthCheckStatus()
            if (statusResponse) {
                healthCheckStatus = "PASSED"
            } else {
                healthCheckStatus = "FAILED"
            }
            loaderState = false
        }
    }
}

@Composable
fun EntryPoint(loginViewModel: EntryPointViewModel = viewModel()) {
    val username = loginViewModel.userName
    val userPasswordState = loginViewModel.userPassword
    var healthStatus by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Welcome to StarTracker Native project",
            )
            Text(
                text = "Run tiny AI models offline!",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp
            )
        ) {
            Text(text = "Enter username:")
            BasicTextField(
                state = username,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                decorator = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                        ) {
                            innerTextField()
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp
            )
        ) {
            Text(text = "Enter password:")
            BasicSecureTextField(
                state = userPasswordState,
                textObfuscationMode = TextObfuscationMode.Hidden,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                decorator = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                        ) {
                            innerTextField()
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.padding(horizontal = 10.dp),
                onClick = {
                    loginViewModel.user_authentication()
                }
            ) {
                Text(text = "Login user")
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = loginViewModel.healthCheckStatus,
                    color = if (loginViewModel.healthCheckStatus == "PASSED") Color.Green else Color.Red
                )
                Spacer(modifier = Modifier.width(10.dp))
                if (loginViewModel.loaderState) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedButton(
                    modifier = Modifier,
                    onClick = {
                        loginViewModel.healthCheck()
                    },
                    shape = RoundedCornerShape(corner = CornerSize(10.dp))
                ) {
                    Text(text = "Health check")
                }
            }
        }
    }
}
