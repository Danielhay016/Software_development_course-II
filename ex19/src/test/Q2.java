package test;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class Q2  extends RecursiveTask<Integer>{
    int[] data;
    public Q2(int[] data){
        this.data=data;
    }

    @Override
    protected Integer compute() {
       if(data.length == 2)
           return Math.max(data[0],data[1]);

       if(data.length==1)
           return data[0];

       int middle = data.length/2;
       int[] left = Arrays.copyOfRange(data , 0,middle);
       int[] right = Arrays.copyOfRange(data , middle,data.length);

       Q2 a = new Q2(left);
       Q2 b = new Q2(right);

       b.fork();


       return Math.max(a.compute(),b.join());





    }
}
