package test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Pipe<E> implements Stoppable{

	public ArrayBlockingQueue<E> queue;
	 public Thread t;
	 Consumer<E> task ;//should not be runnable
	volatile boolean stop;//more than 1 thread
	Stoppable next; //keep the next pipe to stop.
	public Pipe()
	{
		stop = false;
		queue =  new ArrayBlockingQueue<>(100);
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!stop)
				{
						try {
							if(task!=null)
							task.accept(queue.take());//missing consumer מגדירה פעולה מראש
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
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
				queue.put(e);// if q is full - put waiting
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	public Pipe<E> filter(Predicate<E> condition)
	{
		Pipe<E> newPipe = new Pipe<>();
		task=(e)-> {
				if (condition.test(e))
					newPipe.add(e);
		};
		this.next=newPipe;
		return newPipe;

	}
    //template
	public <R> Pipe<R> map(Function<E,R> func)
	{
       Pipe<R> newPipe = new Pipe<>();
	   task=(e)->  newPipe.add(func.apply(e));

	   this.next=newPipe;
	   return newPipe;
	}


     public void forEach(Consumer<E> consumer)
	 {
		 task = consumer;
	 }
	@Override
	public void stop() {
		stop = true;
		t.interrupt();
		if(next!=null)
			next.stop();

	}	
}
