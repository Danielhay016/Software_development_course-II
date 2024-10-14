package test;

public class GoodCode {

	private static double average(int[] array){
		double sum0=0;
		double sum1=0; // sum the values
		int size = array.length;
		int sizeLoop = size/2;
		for(int i=0;i<sizeLoop;i++)
		{
			sum0+=array[i];
			sum0+=array[sizeLoop+i];
		}

		return (sum0+sum1)/size; // average
	}
	
	// returns the squared distance of each value from the average 
	public static double[] dists(int[] array){
		int size = array.length;
		double[] r=new double[size];
		double avg = average(array);
		for(int i=0;i<size;i++)
		{
			double x = Math.abs(array[i]-avg);
			r[i]=x*x;
		}

		return r;
	}
}
