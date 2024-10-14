package test;


import java.util.concurrent.atomic.AtomicInteger;

public class Student {
	
	public static AtomicInteger count = new AtomicInteger(0);
	
	public  Student() {
		count.incrementAndGet();

	}
	
	public static int getCount() {
		return count.get();
	}
	
	// rest code of student...

}
