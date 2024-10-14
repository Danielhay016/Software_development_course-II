package test;

import java.util.concurrent.*;



public class Q1 {
    private static class MyFuture<V> implements Future<V> {

        public volatile boolean cancel = false;
        V v  = null;

        public synchronized void set(V v){
            this.v = v;
            notifyAll();

        }

        @Override
        public synchronized boolean cancel(boolean mayInterruptIfRunning) {
           if(v==null)
           {
               notifyAll();
               cancel=true;
               return true;
           }

            return false;
        }

        @Override
        public boolean isCancelled() {

            return cancel;
        }

        @Override
        public boolean isDone() {
            if(v==null)
            return false;

            return true;
        }

        @Override
        public V get() throws InterruptedException, ExecutionException { //double lock checking
            if(v==null)
            {
                synchronized (this)
                {
                    if(v==null)
                        wait();
                }
            }
            return v;
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
			// no need to implement
            return null;
        }

    }
    
    Runnable task;
    public void execute(){
        if(task!=null)
        task.run();
    }

    public <V> Future<V> submit(Callable<V> c){
        MyFuture<V> f = new MyFuture<>();
        task = ()-> {
            try {
                f.set(c.call());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        return f;
    }
}
