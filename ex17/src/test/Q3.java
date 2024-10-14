package test;

public class Q3 {
    

    public static int[] goodSumCols(int[][] matrix){
        int[] vec=new int[matrix[0].length];
        for(int i=0;i<vec.length;i++){
            for(int j=0;j<matrix.length;j++){
                int x =matrix[i][j];
                boolean p = (x>0);
                vec[j] += p ? x : -x ;
            }
        }
        return vec;
    }



}
