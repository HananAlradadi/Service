package com.example.myapplicationser

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.myapplicationser.ui.theme.MyApplicationSerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cp()
        setContent {
            MyApplicationSerTheme {
                StartService(LocalContext.current)
            }
        }
    }
    private fun cp(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1

            )
        }
    }
}
@Composable
fun StartService(context : Context){
    val serviceIntent = Intent(context , TestService:: class.java)
    Column(Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center) {
      Button(onClick = {
          context.startService(serviceIntent)
      }) {
          Text(text = "Start Service")
      }
        Button(onClick = { serviceIntent.action = STOP_SERVICE
        context.stopService(serviceIntent)}) {
            Text(text = "end Service")
        }
    }

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationSerTheme {
        Greeting("Android")
    }
}