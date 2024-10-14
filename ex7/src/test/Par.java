package test;


import java.util.*;
import java.util.concurrent.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Par {

    public ExecutorService es;
    public Par(int numOfThreads)
    {
        es = Executors.newFixedThreadPool(numOfThreads);
    }

    public <V> Future<V> fold(V[] buff , BinaryOperator<V> f)
    {

        Future<V> res = es.submit(()-> Arrays.stream(buff).reduce(buff[0],f));
        return res;
    }

	public <S,R> Future<List<R>> map(S[] buff , Function<S,R> f)
    {
        Future<List<R>>  res = es.submit(()->Arrays.stream(buff).map(i->f.apply(i)).collect(toList()));

        // Future<List<R>> res = es.submit(Arrays.stream(buff).map(f).collect(toList()));)

      return res;
    }
	public void close()
    {
        es.shutdown();
    }
}

/*
package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Par {
	ExecutorService es;

	public Par(int nThreads) {
		es=Executors.newFixedThreadPool(nThreads);
	}

	public <V> Future<V> fold(V[] buff, BinaryOperator<V> op){
		return es.submit(()->{
			V v=buff[0];
			for(int i=1;i<buff.length;i++)
				v=op.apply(v, buff[i]);
			return v;
		});
	}

	public <V,R> Future<List<R>> map(V[] buff,Function<V,R> func){
		return es.submit(()->{
			List<R> r=new ArrayList<R>();
			for(V v : buff)
				r.add(func.apply(v));
			return r;
		});
	}

	public void close(){
		es.shutdown();
	}
}

 */