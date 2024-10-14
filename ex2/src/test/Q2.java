package test;

public class Q2 {

    // the original code:

    public static double avg(double[] x){ //loop unrolling - less 2x
        double sum0=0;
        double sum1=0;
        for(int i=0;i<x.length/2;i++) {
            sum0 += x[i];
            sum1 += x[i + x.length / 2];
        }
        return (sum0+sum1)/x.length;
    }
/*
    public static double pearson(double[] x,double[] y){
        if(x==y)
            return 1;
        if(x.length==y.length){
            double sumXY=0, sumX=0, sumY=0;
            double  avx = avg(x);
            double   avy = avg(y);
            for(int i=0;i<x.length;i++){
                sumXY+=(x[i]-avx)*(y[i]-avy);
                //sumX+=Math.pow((x[i]-avx),2);
                sumX+=((x[i]-avx)*(x[i]-avx));
               //sumY+=Math.pow((y[i]-avy),2);
                sumY+=((y[i]-avy)*(y[i]-avy));

            }
            return (sumXY)/Math.sqrt((sumX*sumY));
        }
        return 0;
    }
*/


    public static double pearson(double[] x,double[] y){
       // if(x==y)
        //    return 1;
        if(x.length==y.length){
            double avgx=avg(x);
            double avgy=avg(y);
            double sumXY0=0, sumX0=0, sumY0=0;
            double sumXY1=0, sumX1=0, sumY1=0;
            int lenDiv2=x.length/2; // x.length/2
            for(int i=0;i<lenDiv2;i++){
                sumXY0+=(x[i]-avgx)*(y[i]-avgy);
                sumXY1+=(x[i+lenDiv2]-avgx)*(y[i+lenDiv2]-avgy);

                sumX0+=(x[i]-avgx)*(x[i]-avgx);
                sumY0+=(y[i]-avgy)*(y[i]-avgy);

                sumX1+=(x[i+lenDiv2]-avgx)*(x[i+lenDiv2]-avgx);
                sumY1+=(y[i+lenDiv2]-avgy)*(y[i+lenDiv2]-avgy);
            }
            return (sumXY0+sumXY1)/Math.sqrt((sumX0+sumX1)*(sumY0+sumY1));
        }
        return 0;
    }


    public static void warmup(){
        double[] x=new double[100];
        double[] y=new double[x.length];
        for(int i=0;i<x.length;i++){
            x[i]=Math.random();
            y[i]=Math.random();
        }
        for(int i=0;i<=10000;i++){
            pearson(x,y);
        }

    }

}
