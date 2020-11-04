package kim.hsl.handler;

public class Main {

    public static void main(String[] args) {
        Looper.prepare();

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("主线程处理 Handler 事件 : " + msg.obj + " , 线程号 : " + Thread.currentThread());
            }
        };

        new Thread(){
            @Override
            public void run() {
                Message msg = new Message();
                msg.obj = "Hello Handler";
                handler.sendMessage(msg);
                System.out.println("子线程 发送 Message 信息 , 线程号 : " + Thread.currentThread());
            }
        }.start();

        Looper.loop();
        Looper.loop();
    }
}
