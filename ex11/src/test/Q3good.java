package test;

import java.util.List;
import java.util.function.BinaryOperator;

public class Q3good<V> {
	
	List<V> data;
	
	public Q3good(List<V> data) {		
		this.data=data;
	}
	
	public V fold(V identity,BinaryOperator<V> acc) {

	// V result= data.stream().reduce(identity,acc); --> very high  - not good
	// if there is noting to do - do loop unrolling
		V result0=identity;
		V result1=identity;

		int size = data.size();
		for(int i = 0 ; i < size/2 ; i++)
		{
			result0=acc.apply(result0, data.get(i));
			result1=acc.apply(result1, data.get(size/2+i));
		}

		V res = acc.apply(result0,result1);
		return res;
	}
}
/*
public class Q3good<V> {

	List<V> data;

	public Q3good(List<V> data) {
		this.data=data;
	}

	public V fold(V identity,BinaryOperator<V> acc) {
		V result0=identity;
		V result1=identity;

		for(int i=0;i<data.size();i+=2) {
			result0=acc.apply(result0, data.get(i));
			result1=acc.apply(result1, data.get(i+1));
		}

		return acc.apply(result0, result1);
	}

}
*/