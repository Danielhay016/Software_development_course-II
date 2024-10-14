package test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Count {
	AtomicInteger[] countArr;
	
	
	public Count(int size) {
		countArr= new AtomicInteger[size];
		for (int i = 0; i < countArr.length; i++)
			countArr[i] = new AtomicInteger(0);

	}
	
	public  void inc(){
		for (int i = 0; i < countArr.length; i++)
			countArr[i].incrementAndGet();
	}
	
	public  void dec() {
		for (int i = 0; i < countArr.length; i++)
			countArr[i].decrementAndGet();
	}
	
	public int get(int index) {
		return countArr[index].get();
	}
}
