package test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
/*
public class MyTimer  extends TimerTask {

	Runnable r ;
	public static ReentrantLock LOCK = new ReentrantLock();

	public void start(Runnable r, int timesPerSecond) throws Exception{
     Timer t = new Timer();
	 this.r=r;
	 boolean lock =LOCK.tryLock();
	try {
		if(lock)
			t.scheduleAtFixedRate(this,0,1000/timesPerSecond);
		else
			System.out.println("this timer already runs a task");

	}finally {

	}

	}
	
	public void stop() {
		this.cancel();
		LOCK.unlock();
	}

	@Override
	public void run() {
		r.run();
	}
}
*/
public class MyTimer{
	volatile boolean stop;
	Thread t;
	public MyTimer()
	{
		t =null;
	}
	public void start(Runnable r, int timesPerSecond) throws Exception{
		if(t==null)//lock
		{
			stop =false;
			t=new Thread(new Runnable() {
				@Override
				public void run() {
					while(!stop)
					{
						r.run();
						try {
							Thread.sleep(1000/timesPerSecond);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				}
			});
			t.start();
		}else
		{
			throw  new Exception("this timer already runs a task");
		}
	}
	public void stop() {
     stop=true;
	 t.interrupt();
	 t=null;

	}

}