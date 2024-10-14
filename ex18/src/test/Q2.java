package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Q2  extends RecursiveTask<Integer> {
    int[] data;//size = exp 2
    public Q2(int[] data){
        this.data=data;
    }

    @Override
    protected Integer compute() {
        if(data.length == 2)
            return Math.max(data[0],data[1]);
        if(data.length == 1)
            return data[0];

        int newSize= data.length/2;
        int[] dataRight = Arrays.copyOfRange(data , 0 , newSize);
        int[] dataLeft = Arrays.copyOfRange(data , newSize , data.length);;

        Q2 right = new Q2(dataRight);
        Q2 left = new Q2(dataLeft);

        right.fork();

        return (Math.max(left.compute(),right.join()));

    }
}
