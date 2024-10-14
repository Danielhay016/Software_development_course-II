package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2 {
		
	public static int min = Integer.MAX_VALUE;
		public static int H(String a, String b){
		 int sum=0;
		 if( a.compareTo(b) == 0 )
		 {
			return sum;
		 }

		 int size_a =a.length();
		 int size_b =b.length();
		 int minsSize = Math.min(size_a,size_b);

			for(int i=0;i<minsSize;i++){
			if(a.charAt(i)!=b.charAt(i))
			{
				sum++;
			}
				if(sum > min)
					return Integer.MAX_VALUE;
		}
		if(size_a>size_b)
			sum+=size_a-size_b;
		
		if(size_a<size_b)
			sum+=size_b-size_a;
		
		return sum;
	}

	public static int findMinH(ArrayList<String> array){
		for(String a : array)
			for(String b : array)
				if(a!=b){
					int x = H(a,b);
					if( x == 0)
						return x;
					if(min>x)
						min = x;
				}
		return min;
	}
}
