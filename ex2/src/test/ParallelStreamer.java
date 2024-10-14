package test;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class ParallelStreamer<E> {

    ArrayList<BlockingQueue<E>> queues;
    int round;
    int size;
    volatile boolean stop = false;
    Thread[] threads;
    public ParallelStreamer(int size , int capacity , Consumer<E> function) {
        queues = new ArrayList<>(size);
        threads = new Thread[size];
        round = 0;
        this.size = size;

       for(int i = 0 ; i <size ; i ++)
       {
          BlockingQueue<E> q = new LinkedBlockingQueue<>(capacity);
          queues.add(q);
          threads[i] = new Thread(new Runnable() {
              @Override
              public void run() {
                  while (!stop)
                  {
                      try {
                          function.accept(q.take());
                      } catch (InterruptedException e) {
                          throw new RuntimeException(e);
                      }
                  }
              }
          });
           threads[i].start();
       }

    }

    public void add(E e) throws InterruptedException{
        if(!stop){
            queues.get(round).put(e);
            round=(round+1)%size;
        }
    }

    public void endOfInput(){
        stop=true;
        for(int i=0;i<threads.length;i++)
            threads[i].interrupt();
    }

}
