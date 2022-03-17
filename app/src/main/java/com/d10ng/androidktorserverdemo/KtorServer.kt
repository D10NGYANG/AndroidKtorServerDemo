package com.d10ng.androidktorserverdemo

import android.os.Build
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object KtorServer {

    private val server by lazy {
        embeddedServer(Netty, 12345) {
            install(CallLogging)
            // 跨域访问
            install(CORS) {
                anyHost()
                header(HttpHeaders.ContentType)
                method(HttpMethod.Options)
                method(HttpMethod.Put)
                method(HttpMethod.Patch)
                method(HttpMethod.Delete)
            }

            routing {
                get("/") {
                    call.respondText("手机型号 ${Build.MODEL} 运行正常", ContentType.Text.Plain)
                }
            }
        }
    }

    /** 启动服务器 */
    fun start() {
        CoroutineScope(Dispatchers.IO).launch { server.start(true) }
    }

    /** 停止服务器 */
    fun stop() {
        server.stop(1_000, 2_000)
    }
}