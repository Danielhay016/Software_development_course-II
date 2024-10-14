package test;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainTrain1 {

    public static void cancelTest(){
        Q1 q=new Q1();
        Future<String> f =  q.submit(()->"42");
        if (f.isDone())
            System.out.println("isDone error (-5)");
        
        String result[]={null};
        boolean cancel[]={false};
        // cancel test
        new Thread(()->{
            try {
                result[0] = f.get();
                cancel[0]=true;
            } catch (InterruptedException | ExecutionException e) {}
        }).start();
        
        try {Thread.sleep(100);} catch (InterruptedException e) {}

        f.cancel(true);
             
        if(!f.isCancelled() || result[0]!=null)
            System.out.println("cancel error (-8)");

    }

    public static void getTest(){
        Q1 q=new Q1();
        Random r=new Random();
        int x=1+r.nextInt(1000);
        Future<Integer> f =  q.submit(()->x);
        
        int result[]={0};
        boolean wakeup[]={false};
        // cancel test
        new Thread(()->{
            try {
                result[0] = f.get();
                wakeup[0]=true;
            } catch (InterruptedException | ExecutionException e) {}
        }).start();
        
        try {Thread.sleep(100);} catch (InterruptedException e) {}

        q.execute();
        
        try {Thread.sleep(100);} catch (InterruptedException e) {}
             
        if(!f.isDone() || result[0]!=x)
            System.out.println("get or execute error (-8)");

    }

    public static void main(String[] args) {
        int c=Thread.activeCount();
        cancelTest();
        getTest();
        try {Thread.sleep(100);} catch (InterruptedException e) {}
        if(Thread.activeCount()!=c)
            System.out.println("your code leads to unclosed threads (-4)");
        System.out.println("done");
    }
}
