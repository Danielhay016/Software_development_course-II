package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class GenericActiveObject {

    Object o ;
    ExecutorService es;
    Method[] methods;

    public GenericActiveObject(Object o){
        this.o=o;
        Class c = o.getClass();
        methods = c.getMethods();
    }

    public void execute(String name, Object...args){
        if(es == null)
        {
            es = Executors.newSingleThreadExecutor();

        }
        es.execute(new Runnable() {
            @Override
            public void run() {
                for(Method m : methods)
                {
                    if(m.getName() == name)
                    {
                        try {
                            m.invoke(o,args);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }


            }
        });
    }

    public void shutdown(){
        es.shutdown();
    }


}
