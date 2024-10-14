package test;

import java.util.Random;



public class MainTrain3 {
    public static double dist(double x, double y){
        return Math.sqrt(Math.pow(x-y, 2));
    }
	// bad code...
    public static int badContains(int[] arr,int val){
        int r=-1;
        for(int i=0;i<arr.length;i++)
            if(dist(val,0)==dist(arr[i],0))
                r=i;
        return r;
    }

	
	public static void main(String[] args) {
        Random r=new Random();
        int N=10000000;
        int[] arr=new int[N];
        for(int i=0;i<N;i++){
            arr[i]=-N+r.nextInt(N+1);
        }


        int x=arr[5000000+r.nextInt(arr.length-5000000)];
        long t0=System.nanoTime();
        int s=badContains(arr,x);
        long db=System.nanoTime()-t0;

        t0=System.nanoTime();
        int sq3=Q3.goodContains(arr,x);
        long dg=System.nanoTime()-t0;

        if(sq3!=s){
            System.out.println("your code didn't get the desired result (-25)");
            System.out.println("done");
            return;
        }

        System.out.println("~bad time:\t"+db);
        System.out.println("~good time:\t"+dg);
        double ratio=(double)db/(double)dg;
        System.out.println("~ratio: "+ratio);
        
        if(ratio<2){
            int grade=(int)Math.round(25*ratio/2.0);
            if(grade<25)
                System.out.println("you can write a faster code (-"+(25-grade)+")");
        }
        

        System.out.println("done");
    }

}