package test;

import java.util.LinkedList;
import java.util.Queue;

public class GoodCode {


public static double sum = 0 ;

	public static double fixSum(Queue<Double> ds, int window) {

		int sum = 0;
		if (ds.size() > window)
			ds.remove();

		for (Double num : ds)
			sum += num;

		return sum;
	}
	
	public static double calcMean(double sum, double window) {
		return sum/window;
	}
	
	public static double[] SMA_Opt(double[] vec, int window) {
		double[] newArray = new double[vec.length];
		Queue<Double> ds = new LinkedList<Double>();
		for (int i = 0; i < window; i++) {
			sum += vec[i];
			ds.add(vec[i]);
			newArray[i] = sum/window;
		}
		int j = 0;
		for (int i = window ; i < vec.length; i++) {
			sum-=vec[j];
			ds.remove();
			ds.add(vec[i]);
			sum+=vec[i];
			newArray[i] = sum/window;
			j++;
		}

		return newArray;
	}
	
	 
}
