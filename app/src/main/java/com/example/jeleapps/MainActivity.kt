package com.example.jeleapps

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jeleapps.ui.theme.JeleAppsTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //FirebaseApp.initializeApp(this)
        var token by mutableStateOf("")
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            token = if (task.isSuccessful) {
                task.result

            } else {
                "Failed to fetch token"
            }
        }
        setContent {
            MyApp(token)
            Log.i("FCM","Token: $token")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(token: String) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("FCM Token Viewer") })
            }
        ) {
            Surface(modifier = Modifier.padding(it)) {
                TokenScreen(token)
            }
        }
    }
}

@Composable
fun TokenScreen(token: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Firebase Token:", style = MaterialTheme.typography.bodyLarge)
        Text(text = token, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp("SampleToken123")
}
