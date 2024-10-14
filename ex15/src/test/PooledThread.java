package test;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PooledThread{
	Thread t;
	public volatile  boolean stop = false;
	public BlockingQueue<Runnable> queue;

	public PooledThread()
	{
		queue = new LinkedBlockingQueue<>();
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stop)
				{
					while (!queue.isEmpty())
						queue.poll().run();

					try {
						Thread.sleep(1000);
						{
							if(queue.isEmpty())
							{
								stop =true;
							}

						}
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}

				}
			}
		});
		t.start();

	}
	public void add(Runnable task) {
		if(!stop) {
			try {
				queue.put(task);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}


/*
package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PooledThread{

	BlockingQueue<Runnable> queue;

	public PooledThread() {

		queue=new ArrayBlockingQueue<Runnable>(100);
		new Thread(()->{
			Runnable task;
			try {
				while((task=queue.poll(1, TimeUnit.SECONDS))!=null)
					task.run();
			} catch (InterruptedException e) {}
		}).start();
	}


	public void add(Runnable task) {
		try {
			queue.put(task);
		} catch (InterruptedException e) {}
	}

}
 */