package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tasker extends HashMap<String, List<Runnable>>{

	private static final long serialVersionUID = 1L;
	volatile boolean start = false;
	volatile boolean done = false;

	public static Object t;

	public synchronized void start()
	{
		start =true;
		for(String s : this.keySet())
		{
			List<Runnable> runs = this.get(s);
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i =0 ; i < runs.size() ; i++ )
					{
						runs.get(i).run();
					}

				}
			},s);

          t.start();

		}
		done =true;
		notifyAll();
	}
	public void join()
	{
	if(done = false)
	{
		synchronized (this)
		{
			if(done = false)
				try {
					wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
		}
	}
  }

		public boolean put(String s , Runnable ... runList)
	{
		if (!start)
		{
			if(this.containsKey(s))
			{
				for(Runnable r : runList)
					this.get(s).add(r);
			}
			else
			{
               this.put(s,new ArrayList<>());
				for(Runnable r : runList)
					this.get(s).add(r);
			}
			return true;
		}
		else
			return false;
	}
	
}


