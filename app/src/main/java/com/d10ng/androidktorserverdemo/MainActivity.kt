package com.d10ng.androidktorserverdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.d10ng.androidktorserverdemo.ui.theme.AndroidKtorServerDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 启动服务器
        KtorServer.start()

        setContent {
            AndroidKtorServerDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(localIPAddress?: "0.0.0.0")
                }
            }
        }
    }

    override fun onDestroy() {
        // 结束服务器
        KtorServer.stop()
        super.onDestroy()
    }
}

@Composable
fun Greeting(ip: String) {
    Text(text = "请打开局域网中的任意浏览器访问 Http://$ip:12345")
}