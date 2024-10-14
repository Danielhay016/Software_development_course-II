package test;

public class Q2 extends Thread{

    Runnable task;
    volatile boolean stop;

    public Q2() {
        stop=false;
    }
    public synchronized boolean setTask(Runnable r) {
        if(task==null) {
            task=r;
            notify();
            return true;
        }
        return false;
    }

    public void run() {
        while(!stop) {
            if(task!=null)
                task.run();

            synchronized (this) {
                if(!stop)
                    try {
                        task=null;
                        wait();
                    } catch (InterruptedException e) {}
            }
        }
    }

    public synchronized void close() {
        stop=true;
        notify();
    }

}
