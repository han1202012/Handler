package kim.hsl.handler;

public class MessageQueue {

    /**
     * 该队列是一个链表 , 因此这里只给出第一个 Message 即可
     */
    Message mMessage;

    /**
     * 将 Message 消息加入到 Message 链表中
     * @param msg
     */
    public void enqueueMessage( Message msg ){
        // 因为 该消息队列 可能会有多个线程 通过 Handler 向消息队列中添加消息
        // 因此 需要使用同步代码块包裹以下逻辑
        synchronized (this){
            if( mMessage == null ){
                mMessage = msg;
            }else{
                /*
                    如果链表不为空
                    这里需要循环查找消息队列的最后一个消息
                    将本次传入的 Message msg 参数加入到链表尾部
                 */
                Message pointer = mMessage;
                Message previous = pointer;
                for(;;){
                    // 记录上一条消息, 每次遍历都将本次遍历的记录下来
                    previous = pointer;

                    // 将 pointer 指向下一条消息
                    pointer = pointer.next;

                    // 此时如果某个 Message 的 下一个元素为空
                    // 说明该 Message 是消息队列最后一个元素
                    if(pointer == null){
                        break;
                    }
                }
                // 将本次参数传入的 Message 放到链表最后
                previous.next = msg;
            }
            notify();
        }
    }

    /**
     * 从消息队列中获取消息
     * @return
     */
    public Message next(){
        synchronized (this){
            // 本次要获取的消息, 最后要返回到 Looper 中 loop 方法中
            Message result;

            for (;;){
                // 尝试和获取 消息队列 链表中的第一个元素
                result = mMessage;
                if(result == null){
                    // 如果当前的 Message 队列为空 , 阻塞等待 , 直到新的消息到来
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    // 如果不为空 , 说明已经获取到最终的消息 , 退出循环即可
                    break;
                }
            }

            // 处理链表逻辑 , 将表头指向下一个 Message
            mMessage = mMessage.next;

            return result;
        }
    }





}
