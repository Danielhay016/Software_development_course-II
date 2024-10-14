package test;


import java.util.function.Consumer;
import java.util.function.Function;

public class MyFuture<V> {
	public V v = null;
    Runnable task = null;
	public void set(V v)
	{
		this.v = v;
        if(task!=null)
            task.run();
	}
	public <R> MyFuture<R> thenDo(Function<V,R> f)
	{
        MyFuture<R> d = new MyFuture<>();
        task=()->d.set(f.apply(v));
        return d;
	}

	public  MyFuture<Void> finallyDo(Consumer<V> f)
	{
		MyFuture<Void> d = new MyFuture<>();
        task=()->f.accept(v);
		return d;
	}
}
