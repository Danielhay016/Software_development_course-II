package test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;

public class PTasker {

	public ExecutorService es = Executors.newSingleThreadExecutor();
	public  <V> Future<V> apply(List<V> buff, V identity, BinaryOperator<V> bo){


		Future<V> sum = es.submit(()-> buff.stream().reduce(identity,bo));

		return sum;

	}
	
	public void close(){

		es.shutdown();

	}
}
