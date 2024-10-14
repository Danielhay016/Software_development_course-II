
package test;

public class MainTrain2 {


	public static class TestRunnabel implements Runnable{
		private static int count=0;
		@Override
		public void run() {
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			count++;
		}
		public static int getCount() {
			return count;
		}
	}
	
	public static void main(String[] args) {
		
		int c=Thread.activeCount();
		Q2 t=new Q2();
		
		t.start(); // starts the thread. the thread should wait for a task
		
		if(Thread.activeCount()!=c+1) {
			System.out.println("you did not open a thread (-25)");
			System.out.println("done");
			return;
		}
	
		TestRunnabel testRunnable=new TestRunnabel();
		if(!t.setTask(testRunnable)) {
			System.out.println("you did not set a task when you should have (-5)");
		}
		if(t.setTask(new TestRunnabel())) {
			System.out.println("you set a task when you should not have (-5)");
		}
		
		try {Thread.sleep(2000);} catch (InterruptedException e) {}
		// task should be now over

		if(!t.setTask(testRunnable)) {
			System.out.println("you did not set a task when you should have (-5)");
		}
		if(t.setTask(new TestRunnabel())) {
			System.out.println("you set a task when you should not have (-5)");
		}
		
		try {Thread.sleep(1500);} catch (InterruptedException e) {}
		
		if(TestRunnabel.getCount()!=2)
			System.out.println("count error of running the tasks (-5)");
		
		t.close();
		try {Thread.sleep(1500);} catch (InterruptedException e) {}
		
		if(Thread.activeCount()!=c) {
			System.out.println("your thread is still running (-5)");
		}
		
		System.out.println("done");
	}


}
