package test;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<E> implements Stoppable{



	BlockingQueue<E> queue;
	Thread t;
	volatile boolean stop = false;

	Consumer<E> c =null;
	Stoppable next = null;


	public MyStream()
	{
		queue= new LinkedBlockingQueue<>(100);
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop)
				{
					if(c!=null)
					{
						try {
							c.accept(queue.take());
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}

				}

			}
		});
		t.start();
	}


	public void add(E e)
	{
		if(!stop) {
			try {
				queue.put(e);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}


  public MyStream<E> filter(Predicate<E> p)
  {
	  MyStream<E> newOne  = new MyStream<>();
	  c=e->{if(p.test(e)) newOne.add(e);};
	  next=newOne;
	  return newOne;

  }

	public <R> MyStream<R> map(Function<E,R> func) {

		MyStream<R> newOne  = new MyStream<>();
		c=e->newOne.add(func.apply(e));
		next=newOne;
		return newOne;
	}

	public void forEach(Consumer<E> c)
	{
		this.c=c;
	}

	// and stop
	@Override
	public void stop() {
		stop = true;
		t.interrupt();
		if(next!=null)//should remember
		next.stop();

	}


}
