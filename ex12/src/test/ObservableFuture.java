package test;

import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
//הפיתרון למטה !
public class ObservableFuture<V> extends Observable {

    public Future<V> f;
    V v;
    Thread t;
    public ObservableFuture(Future<V> vFuture)
    {
        v = null;
        this.f = vFuture;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(v==null)
                {
                    try {
                        f.get();

                        setChanged();
                        notifyObservers();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        t.start();
        //if(v!=null)
          //  setChanged();

    }


	
	public V get()
    {
        if(v!=null)
            return v;// f.get ( means to Future no waiting cause v return)

        return null;
    }
}
/*

public class ObservableFuture<V> extends Observable{

	V v;
	public ObservableFuture(Future<V> f) {
		new Thread(()->{
			try {
				v=f.get();
			} catch (InterruptedException e) {}
			catch (ExecutionException e) {}
			setChanged();
			notifyObservers();
		}).start();
	}

	public V get() {
		return v;
	}
}
 */