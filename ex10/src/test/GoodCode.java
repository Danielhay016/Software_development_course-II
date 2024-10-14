package test;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoodCode {

	public static double dist(Point a,Point b) {
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	public static Set<Point> inCircles(Circle[] cs, List<Point> ps){
		HashSet<Point> hs=new HashSet<>();
		// for each point
		for(Point pi : ps)
		 {  // for each circle
			 for(int i=0 ; i<cs.length ; i++) {
				//ci.center.x-ci.r<= pi.x && ci.center.x+ci.r >= pi.x && ci.center.y-ci.r<= pi.y && ci.center.y+ci.r >= pi.y -   fit to Square !
				if(dist(pi, cs[i].center)<=cs[i].r)
					hs.add(pi);
/*
				if(dist(pi, cs[i].center)>cs[i].r) // pay attention to the condition  >  .
					continue; // i++
				else
					hs.add(pi);

				break; // don't want to check other circles
*/

			}
		}
		return hs;
	}

}
