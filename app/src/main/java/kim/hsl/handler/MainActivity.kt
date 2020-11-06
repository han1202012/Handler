package kim.hsl.handler

import android.os.*
import android.os.Handler
import android.os.Looper
import android.os.MessageQueue
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var handlerThread : HandlerThread;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
