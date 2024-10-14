package test;

public class Q4 {

/*
    public static int goodCode(int[][] matrix){
        int sum=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=matrix.length-1 ;j>=i;j--){//not cache friendly
                sum+=matrix[i][j];

            }
        }
        return sum;
    }
    */


    public static int goodCode(int[][] matrix){
        int sum=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=i;j<matrix[i].length;j++){
                sum+=matrix[i][j];
            }
        }
        return sum;
    }


}
