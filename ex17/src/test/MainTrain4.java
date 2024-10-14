package test;

import java.util.Random;

public class MainTrain4 {

	// bad code...
    public static int badCode(int[][] matrix){
    	int sum=0;
        for(int i=0;i<matrix.length;i++){
        	for(int j=0;j<matrix[i].length;j++){
        		if(j>=i)
        			sum+=matrix[i][j];
            }
        }
        return sum;
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
        int vb=badCode(matrix);
        long db=System.nanoTime()-t0;

        t0=System.nanoTime();
        int vg=Q4.goodCode(matrix);
        long dg=System.nanoTime()-t0;

        if(vg!=vb){
            System.out.println("your code didn't get the desired result (-25)");
            System.out.println("done");
            return;
        }
        
        System.out.println("bad time: "+db);
        System.out.println("good time: "+dg);
        double ratio=(double)db/(double)dg;
        System.out.println("ratio: "+ratio);
        
        if(ratio<4){
            int grade=(int)Math.round(25*ratio/4);
            System.out.println("you can write a faster code (-"+(25-grade)+")");
        }
        

        System.out.println("done");
    }

}
