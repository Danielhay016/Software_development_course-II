package test;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class GenericActiveActor extends Actor{

    Consumer<String> cs;
    Thread t ;
    BlockingQueue<String> stringBlockingQueue;
   // volatile boolean stop = false;

    public GenericActiveActor(int id , Consumer<String> stringConsumer) {
        super(id);
        this.cs = stringConsumer;
        stringBlockingQueue = new LinkedBlockingQueue<>();
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(stringBlockingQueue.peek() != "stop")
                {
                    try {
                        stringConsumer.accept(stringBlockingQueue.take());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t.start();
    }

    @Override
    void addMessage(String msg) {
        if(stringBlockingQueue.peek() != "stop")
        {
            try {
                stringBlockingQueue.put(msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    void close() {
       addMessage("stop");
       t.interrupt();
    }
}
