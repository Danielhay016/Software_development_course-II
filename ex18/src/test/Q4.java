package test;


import java.util.ArrayList;
import java.util.Arrays;

public class Q4 {

    public static int goodCode(int[] arr,int val){
        ArrayList<Integer> al=new ArrayList<>(arr.length);
        for(int a : arr)    // O(N)
            al.add(a);

        al.sort((x,y)->x-y); // O(NlogN)

        return Math.abs(val - al.get(al.size()/2));
    }



    /*
    public static boolean isMean(int[]arr, int val){
        int countBelow=0;
        for(int i=0;i<arr.length;i++){
            countBelow+= val>arr[i] ? 1 : 0 ;

        }
        return countBelow==arr.length/2;
    }
    public static int goodCode(int[] arr,int val){
        for(int i=0;i<arr.length;i++)
        {
            if(isMean(arr, arr[i]))
                return Math.abs(arr[i]-val);
        }


        return -1;
    }

     */

}
