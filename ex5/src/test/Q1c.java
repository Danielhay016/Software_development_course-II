package test;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.function.BinaryOperator;

public  class  Q1c {

	public static <T> T recThis(ExecutorService es, T[] array, int start, int end, BinaryOperator<T> f) throws Exception {
		if (end - start == 2)
			f.apply(array[start], array[start + 1]);


		int mid = start + (end - start);
		Future<T> left = es.submit(() -> recThis(es, array, start, mid, f));
		T right = recThis(es, array, mid, end, f);
		return f.apply(left.get(), right);

	}
}