package kim.hsl.handler

import android.os.*
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.MessageQueue
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    lateinit var handlerThread : HandlerThread;
    lateinit var looper : Looper
    lateinit var messageQueue : MessageQueue
    lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val h = kim.hsl.handler.Handler()
        h.sendMessage(kim.hsl.handler.Message())

        handler()
    }

    fun handler(){
        // 1 . 初始化, 之后马上启动
        handlerThread = HandlerThread("handler")
        handlerThread.start()

        // 2 . 获取 Looper
        looper = handlerThread.looper

        // 3 . 获取 消息队列 MessageQueue
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            messageQueue = looper.queue
        }else{
            // 反射获取 MessageQueue 消息队列
            // 设置访问字段
            var mQueue = Looper::class.java.getDeclaredField("mQueue")
            // 设置允许访问
            mQueue.isAccessible = true
            messageQueue = mQueue.get(looper) as MessageQueue
        }

        // 4 . 注册 IdleHandler
        messageQueue.addIdleHandler {
            Log.i(TAG, "空闲任务")
            // 注意这里返回 true, 表示每次空闲任务都执行一次
            true
        }

        // 5 . 初始化 Handler
        handler = Handler(looper, {msg: Message ->
            Log.i(TAG, "运行任务 ${msg.what}")
            true
        })

        // 6 . 子线程中发送消息 0
        thread (start = true) {
            handler.sendEmptyMessage(0)
        }
        // 7 . 主线程发送消息 1
        handler.sendEmptyMessage(1)

    }

}
