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
        // 先进行判断 , 如果当前线程已经有了 Looper , 那就抛出异常
        if(sThreadLocal.get() != null){
            throw new RuntimeException("当前线程已存在 Looper");
        }

        // 如果不存在 Looper , 就创建一个 Looper
        sThreadLocal.set(new Looper());
    }

}
