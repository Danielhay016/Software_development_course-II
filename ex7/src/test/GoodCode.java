package test;

import java.awt.Point;

public class GoodCode {

	private static double EucDistance(Point a, Point b){
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));


	}
	public static Point geoMedian(Point[] values){
		Point r0=null;
		Point rAvg = new Point();
		for(Point p : values)
		{
			rAvg.x +=p.x;
			rAvg.y +=p.y;
		}
		rAvg.x =rAvg.x/ values.length;
		rAvg.y =rAvg.y/ values.length;
		double min=Double.MAX_VALUE;
        int radius = 100;
		for(Point a: values){
			if(rAvg.x-radius<a.x&& a.x<rAvg.x+radius && rAvg.y-radius<a.y && a.y<rAvg.y+radius )
			{
				double sum=0;
				for(Point b : values){
					sum+=EucDistance(a, b);
					if(sum>min)
						break;
				}
				if(min>sum){
					min=sum;
					r0=a;
				}
			}
		}
		return r0;
	}

}
/*
import java.awt.Point;

public class GoodCode {

	private static double EucDistance2(Point a, Point b){
		return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
	}

	public static Point geoMedian(Point[] values){

		double[] sum=new double[values.length];
		for(int i=0;i<values.length;i++){
			for(int j=i+1;j<values.length;j++){
				double d=EucDistance2(values[i], values[j]);
				sum[i]+=d;
				sum[j]+=d;
			}
		}

		Point r=null;
		double min=Double.MAX_VALUE;
		for(int i=0;i<sum.length;i++)
			if(min>sum[i]){
				min=sum[i];
				r=values[i];
			}
		return r;
	}

}

 */