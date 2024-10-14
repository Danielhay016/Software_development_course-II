package test;

import java.util.HashMap;

public class Q2 {

    public static double px(double x, double[] vec){
        int count=0;
        for(int i=0;i<vec.length;i++)
            if(x==vec[i])
                count++;
        return (double)count/vec.length;
    }

    public static double log2(double x){
        return Math.log10(x)/Math.log10(2);
    }


    public static double OPT_Hx(double[] vec){
        double sum0=0;
        double tmp = 0 ;
        HashMap<Double,Double> px = new HashMap<>();
        for(int i=0;i<vec.length;i++) {
            if(px.get(vec[i]) == null ) {

                tmp = px(vec[i], vec) * log2(px(vec[i], vec)) ;
                px.put(vec[i],tmp);
            }
            sum0 +=px.get(vec[i]);


        }
        return -(sum0);
    }
}
