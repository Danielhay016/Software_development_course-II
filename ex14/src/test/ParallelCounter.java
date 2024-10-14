package test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ParallelCounter {

    public static  <V>  int  parallelCountIf(List<V> list , Predicate<V> p , int numOfThreads)
    {
        AtomicInteger count = new AtomicInteger(0);
        int  numOfvar = list.size()/numOfThreads;
        Thread[] threads = new Thread[numOfThreads];
        for(int i = 0; i < numOfThreads ; i++)
        {

            List<V> threadList = list.subList(i*numOfvar,(i+1)*numOfvar);
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for( V v : threadList )
                    {
                        if(p.test(v))
                            count.incrementAndGet();

                        else
                            count.decrementAndGet();
                    }

                }
            });
            threads[i].start();

        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

     return count.get();

    }

}
