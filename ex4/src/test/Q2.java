package test;

public class Q2 {



    static double[] averageCols(double[][] data){
        int colSize = data[0].length;
        int rowSize = data.length;
        double avg[]=new double[colSize];

        for(int row=0;row<rowSize;row++){
                for(int col = 0; col < colSize ; col++) {
                        avg[col]+=data[row][col];
                }
        }

            for(int i=0;i<avg.length;i++)
                    avg[i]/=data.length;
            return avg;


    }


}