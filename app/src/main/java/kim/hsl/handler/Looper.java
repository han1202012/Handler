package kim.hsl.handler;

public class Looper {

    /**
     * 一个线程只能有一个 Looper
     * 使用 ThreadLocal 来保存该 Looper
     * 是线程内部存储类 , 只能本线程才可以得到存储的数据 ;
     */
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    /**
     * 消息队列
     */
    public MessageQueue mQueue;

    /**
     * Looper 构造函数
     */
    private Looper(){
        mQueue = new MessageQueue();
    }

    /**
     * 获取当前线程对应的 Looper
     * @return
     */
    public static Looper looper(){
        return sThreadLocal.get();
    }



    /**
     * 准备 Looper 方法
     */
    public static void prepare(){
        System.out.println("prepare 创建 Looper ");
        // 先进行判断 , 如果当前线程已经有了 Looper , 那就抛出异常
        if(sThreadLocal.get() != null){
            throw new RuntimeException("当前线程已存在 Looper");
        }

        // 如果不存在 Looper , 就创建一个 Looper
        sThreadLocal.set(new Looper());
    }

    /**
     * 不断从 消息队列 MessageQueue 中取出 Message 消息执行
     */
    public static void loop(){
        System.out.println("开始无限循环获取 Message");

        // 获取当前线程的 Looper
        Looper looper = Looper.looper();

        // 从当前线程的 Looper 获取 消息队列 MessageQueue
        MessageQueue messageQueue = looper.mQueue;

        // 不断从 消息队列中获取 消息 , 分发到发送消息的 Handler 中执行
        for(;;){
            // 获取消息队列中的第一个消息
            Message next = messageQueue.next();
            // 分发到发送该消息的 Handler 中执行
            next.target.handleMessage(next);
        }
    }

}
