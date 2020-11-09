package kim.hsl.handler;

@Keep
public class Handler {

    /**
     * 消息队列
     * 该消息队列封装在 Looper 中
     * Looper 封装在线程本地变量中
     */
    MessageQueue mQueue;

    public Handler(){
        /*
            在 Handler 中需要拿到 Looper
            进而拿到 Looper 中的 MessageQueue 消息队列

            Handler 的操作就是将 Message 放入 MessageQueue
            因此在 Handler 中需要持有 MessageQueue 消息队列的引用


            获取 Looper 时 , Looper 必须已经初始化完毕,
            也就是已经调用过 prepare 创建了 Looper 并将其放入了线程本地变量
         */

        // 获取当前线程中的 线程本地变量 Looper
        Looper looper = Looper.looper();

        // 获取封装在 Looper 中的 消息队列 MessageQueue
        mQueue = looper.mQueue;
    }

    /**
     * 发送消息
     * @param msg
     */
    public void sendMessage(Message msg){
        // 为消息设置发送的 Handler
        msg.target = this;
        // 向消息队列中放入要执行的消息
        mQueue.enqueueMessage(msg);
    }


    /**
     * 执行消息对应的任务
     * @param next
     */
    public void handleMessage(Message next) {

    }
}
