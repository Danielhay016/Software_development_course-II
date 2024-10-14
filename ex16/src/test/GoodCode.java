
package test;

import java.util.List;

public class GoodCode {

	private static double dist(Point2D a, Point2D b) {
		return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
	}
	
	public static double getMaxDist(List<Point2D> ps) {
		double max=0;
		int size = ps.size();

		for( int i = 0  ; i < size ; i++ )
		{
			Point2D iPoint = ps.get(i);
			for(int j = i+1 ; j < size ; j++)
			{
				double x = dist(iPoint,ps.get(j));
				if(max<x)
					max=x;
			}
		}

		return Math.sqrt(max);

	}
	


}
