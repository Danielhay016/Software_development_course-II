package test;
import java.util.concurrent.RecursiveTask;

public class LucasNumbers extends RecursiveTask<Integer> {

    int index ;
    public LucasNumbers(int num)
    {
        this.index = num;
    }

	public Integer compute() {
        if ( index == 0 )
             return 2;
        if ( index == 1 )
             return 1;
        LucasNumbers lcNLeft = new LucasNumbers(index-1);
        LucasNumbers lcNRight = new LucasNumbers(index-2);
        lcNLeft.fork();

        return lcNRight.compute() + lcNLeft.join();


	}
	
}
