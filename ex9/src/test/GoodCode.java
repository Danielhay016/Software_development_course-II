package test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class GoodCode {


	public static int common(int[] grades) {


     int[] common = new int[101];
     int max = 0;
     int grade = 0;
     for(int g : grades)
     {
         int tmp = ++common[g];
         if(max<tmp)
         {
             max=tmp;
             grade = g;
         }

     }
        /*
        int common=0;
        int max = 0 ;
        HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		for(int g : grades) {
			if(!map.containsKey(g))
				map.put(g, 1);
			else {
                int count = map.get(g);
                map.replace(g, count+1);
                count =map.get(g);
                if(count>max)
                {
                    max = count;
                    common = g;

                }
            }
		}
 */
        return grade;
	}
}
