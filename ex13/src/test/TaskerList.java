package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class TaskerList extends LinkedList<BlockingQueue<Runnable>>{

	private static final long serialVersionUID = 1L;

	volatile boolean stop = false;
	ArrayList<Thread> threads=new ArrayList<>();


	public void pollAll() {

		forEach((queues)->{

			Thread t = new Thread(()->{
				while(!queues.isEmpty())
					try {
						queues.take().run();
					} catch (InterruptedException e) {}
			});
			t.start();
			threads.add(t);
		});
	}

	@Override
	public void addLast(BlockingQueue<Runnable> e) {
		if(!stop)
			super.addLast(e);
	}

	public void stopRunning() throws InterruptedException {
		this.stop = true;
		threads.forEach(t-> t.interrupt());
	}
}
/*
public class TaskerList extends LinkedList<BlockingQueue<Runnable>>{

	private static final long serialVersionUID = 1L;
	 volatile  boolean stop = false;
     ArrayList<Thread> threads;

	public void pollAll()
	{
        threads = new ArrayList<>();
		for (BlockingQueue<Runnable> q : this)
		{
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!q.isEmpty()) {
						try {
							q.take().run();
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			});
			t.start();
			threads.add(t);
		}
	}


	public void stopRunning()
	{
		this.stop = true;
		threads.forEach(t-> t.interrupt());

	}

    @Override
	public void addLast(BlockingQueue<Runnable> bq)
	{
		if(!stop)
		super.addLast(bq);
	}
	
}
*/