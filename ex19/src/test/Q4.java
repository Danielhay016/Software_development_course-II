package test;


import java.util.ArrayList;
import java.util.List;

public class Q4 {

    public static boolean isMean(int[]arr, int val){
        int countBelow=0;
        for(int i=0;i<arr.length;i++){
            if(val>arr[i])
                countBelow++;
        }

        return countBelow == arr.length/2;
    }

    public static int goodCode(int[] arr,int val){

        List<Integer> sortedList = new ArrayList<>();
        for(int i = 0 ; i < arr.length ; i++)
        {
            sortedList.add(arr[i]);//O(n)
        }

        sortedList.sort((x,y)->x-y);

        return Math.abs(sortedList.get(arr.length/2) - val);


    }


}
