package test;

public class Q3 {

    public static double dist(double x, double y){
        return (x-y);
    }
    public static int goodContains(int[] arr,int val){

        int r=-1;
        for(int i=0;i<arr.length;i++)
             r = val==arr[i] ? i : r;

        return r;


    }
    
}
