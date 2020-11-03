package kim.hsl.handler;

public class Message {

    /**
     * 消息识别码
     */
    int what;

    /**
     * 消息对象
     */
    Object obj;

    /**
     * 指向下一个消息
     */
    Message next;

    /**
     * 该 Message 使用哪个 Handler 进行发送的
     */
    Handler target;
}
