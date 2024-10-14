package test;

import java.util.HashMap;

public class GoodCode {
	static HashMap<Double,Double> expMap = new HashMap<>();
	public static double expSum(double[] vec){
		double sum=0;
		for(int i=0;i<vec.length;i++)
		{
			sum+= (expMap.get(vec[i]) != null ? expMap.get(vec[i]): exp(vec[i]));

		}
		return sum;
	}
	public static double exp(double z){
		expMap.put(z,Math.exp(z));
		return expMap.get(z);
	}
	public static double[] softmaxOpt(double[] vec){
		double expSum = expSum(vec);
		for(int i=0;i<vec.length;i++) {
			vec[i]=exp(vec[i])/expSum;
		}
		return vec;
	}
	 
}
