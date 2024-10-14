package test;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Q2 {
    public static <V> int parallelCountIf(List<V> list, Predicate<V> p, int numOfThreads){
        AtomicInteger count=new AtomicInteger(0);
        int psize=list.size()/numOfThreads;
        Thread arr[]=new Thread[numOfThreads];
        for(int i=0;i<numOfThreads;i++){
            List<V> partList=list.subList(i*psize, (i+1)*psize);
            arr[i]=new Thread(()->partList.forEach(
                    x-> {if(p.test(x)) count.incrementAndGet();}));

            arr[i].start();
        }

        for(Thread t : arr)
            try {t.join();} catch (InterruptedException e) {}

        return count.get();
    }
}