package test;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class Q1a {
	
	ExecutorService es;
	
	public Q1a() {
		es=Executors.newSingleThreadExecutor();
	}

	
	public void close(){
		es.shutdown();
	}
	


	 public <V> Future<V> threadIt(Callable<V> func)
	 {
		 Future<V> future = es.submit(func);

		 return future;

	 }
}
