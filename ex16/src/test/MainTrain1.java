package test;

import java.util.Random;

public class MainTrain1 {
	

	public static void main(String[] args) {
		
		Random r=new Random();
		int R = 100000 + r.nextInt(100000);
		final int C=R*10;
		
		Thread ts[]=new Thread[10];
		for(int i=0;i<10;i++) {
			ts[i]=new Thread(()->{
				for(int j=0;j<C/10;j++) {
					Student s=new Student();
				}
			});
			ts[i].start();
		}
		
		try {
			for(Thread t : ts)
				t.join();
		} catch (InterruptedException e) {}
		
		
		
		if(C!=Student.getCount())
			System.out.println("expected "+C+" students but got "+Student.getCount()+" (-"+(30-Math.round(30.0*Student.getCount()/C))+")");

		System.out.println("done");		
	}

}
