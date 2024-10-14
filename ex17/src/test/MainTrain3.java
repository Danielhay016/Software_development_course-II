package test;

import java.util.Random;



public class MainTrain3 {

	// bad code...
    public static int[] badSumCols(int[][] matrix){
        int[] vec=new int[matrix[0].length];
        for(int j=0;j<vec.length;j++){
            for(int i=0;i<matrix.length;i++){
                if(matrix[i][j]>0)
                    vec[j]+=matrix[i][j];
                else
                    vec[j]+=-matrix[i][j];
            }
        }
        return vec;
    }
    
	
	public static void main(String[] args) {
        Random r=new Random();
        int N=10000;
        int[][] matrix=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                matrix[i][j]=r.nextInt(101)-50;
        }

        long t0=System.nanoTime();
        int[] vb=badSumCols(matrix);
        long db=System.nanoTime()-t0;

        t0=System.nanoTime();
        int[] vg=Q3.goodSumCols(matrix);
        long dg=System.nanoTime()-t0;

        if(vg==null || vg.length!=vb.length){
            System.out.println("your code didn't get the desired result (-25)");
            System.out.println("done");
            return;
        }
        boolean eq=true;
        for(int i=0;i<vg.length && eq;i++)
            if(vg[i]!=vb[i])
                eq=false;
        if(!eq){
            System.out.println("your code didn't get the desired result (-25)");
            System.out.println("done");
            return;
        }

        System.out.println("bad time: "+db);
        System.out.println("good time: "+dg);
        double ratio=(double)db/(double)dg;
        System.out.println("ratio: "+ratio);
        
        if(ratio<15){
            int grade=(int)Math.round(25*ratio/15.0);
            System.out.println("you can write a faster code (-"+(25-grade)+")");
        }
        

        System.out.println("done");
    }

}
