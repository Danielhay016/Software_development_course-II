package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class Q2good {
	List<Double> X,Y;
	HashMap<Double,Double> py;
	public Q2good(List<Double> X, List<Double> Y) {
		this.X=X;
		this.Y=Y;
		py = new HashMap<>();
	}

	// probability to see yj in Y
	private double Py(double yj) {

		if(py.get(yj)!=null) {
			return py.get(yj);
		}

		double count=0;
		int size =Y.size();
		for(int i=0;i<size;i++)
		{
			if(Y.get(i)==yj)
				count++;
		}
		py.put(yj,(count)/size);
		return py.get(yj);
	}

	// probability to see xi and yj in X and Y
	private double Pxy(double xi, double yj) {
		double count=0;
		int size = X.size();
		for(int i=0;i<size;i++)
			if(X.get(i)==xi && Y.get(i)==yj)
				count++;
		return count/size;
	}

	public double conditionalEntropy() {
		double sum=0;
		double log2 = Math.log10(2);
		int sizeX=X.size();
		int sizeY=Y.size();

		// calculate once Py(yi)
		double py[]=new double[Y.size()];
		for(int i=0;i<py.length;i++)
			py[i]=Py(Y.get(i));

		for(int i=0;i<sizeX;i++) {
			for(int j=0;j<sizeY;j++) {
				double pxy = Pxy(X.get(i),Y.get(j));
				if(pxy>0)
					sum+=pxy*Math.log10(pxy/py[j])/log2;
			}
		}

		return -sum;
	}
}
