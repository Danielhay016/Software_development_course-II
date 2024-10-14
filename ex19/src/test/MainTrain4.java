package test;

import java.util.Random;



public class MainTrain4 {
    public static boolean isMean(int[]arr, int val){
        int countBelow=0, countAbove=0;
        for(int i=0;i<arr.length;i++){
            if(val>arr[i])
                countBelow++;
            if(val<arr[i])
                countAbove++;
        }

        return countAbove==countBelow;
    }
	// bad code...
    public static int badCode(int[] arr,int val){

        // search for the mean
        for(int i=0;i<arr.length;i++)
            if(isMean(arr, arr[i])){
                return Math.abs(arr[i]-val);
            }
        
        return -1;
    }

	
	public static void main(String[] args) {
        Random r=new Random();
        int N=70001;
        int[] arr=new int[N];
        for(int i=0;i<N;i++){
            //int x=-N*2+r.nextInt(N*2+1);
            boolean found=true;
            int x=-N+r.nextInt(N*2+1);
            while(found){
                found=false;
                for(int j=0;j<i;j++)
                    if(arr[j]==x){
                        found=true;
                        x=-N+r.nextInt(N*2+1);
                        break;
                    }
            }
            arr[i]=x;
        }


        int x=arr[r.nextInt(arr.length)];
        long t0=System.nanoTime();
        int s=badCode(arr,x);
        long db=System.nanoTime()-t0;

        t0=System.nanoTime();
        int sq4=Q4.goodCode(arr,x);
        long dg=System.nanoTime()-t0;

        if(sq4!=s){
            System.out.println("your code didn't get the desired result (-25)");
            System.out.println("done");
            return;
        }

        System.out.println("~bad time:\t"+db);
        System.out.println("~good time:\t"+dg);
        double ratio=(double)db/(double)dg;
        System.out.println("~ratio: "+ratio);
        
        if(ratio<10){
            int grade=(int)Math.round(25*ratio/10.0);
            if(grade<25)
                System.out.println("you can write a faster code (-"+(25-grade)+")");
        }
        

        System.out.println("done");
    }

}