package test;


import java.util.concurrent.RecursiveTask;

public class FJ_GCD  extends RecursiveTask<Integer> {

    int a , b ;
    public FJ_GCD(int a , int b)
    {
       this.a = a;
       this.b = b;
    }

    @Override
    protected Integer compute() {
        if(b==0)
            return a;
        FJ_GCD tmp = new FJ_GCD(b,a%b);
        tmp.fork();
        return tmp.join();
    }
}
