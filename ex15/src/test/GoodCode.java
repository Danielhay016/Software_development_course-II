package test;

import java.util.*;

public class GoodCode {

		private static double dist(Point2D a, Point2D b) {
			return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
		}

		private static int within(List<Point2D> ps, Point2D center, int r) {
			int count=0;
			for(Point2D pi : ps) {
				count+=(dist(center, pi)<=r) ? 1 : 0 ;
			}
			return count;
		}

		public static int getRad(List<Point2D> ps, Point2D center,int percent) {

			int r=10;
			int p = 0 ;
			int size = ps.size();
			p =(int) (100.0 * within(ps, center, r)/size);
			while(p < percent){
				r+=15;
				p =(int) (100.0 * within(ps, center, r)/size);
			}
			if(p==percent)
			return r;

				int round = p - percent;
				for (int i = 0; i < round; i++) {
					while(p > percent){
						r--;
						p =(int) (100.0 * within(ps, center, r)/size);
					}
				}

				return r;

		}



	}