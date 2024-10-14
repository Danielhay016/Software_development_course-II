package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Q1b {
	BlockingQueue<Runnable> tasks;
	public volatile static boolean stop  = false;
	public Q1b()
	{
		tasks = new LinkedBlockingQueue<>();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop)
				{
					try {
						tasks.take().run();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
		thread.start();
	}
	public void push(Runnable r){
		if(!stop) {
			try {
				tasks.put(r);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
	public void close(){
		push(()->stop=true);

	}
}