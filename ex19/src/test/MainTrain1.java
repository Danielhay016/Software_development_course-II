package test;

import java.util.Random;

public class MainTrain1 {
    public static class A{
        int x;
        public void init(){ x=0;}
        public void inc(){x++;}
        public void incl(int len){x+=len;}
        public void set(int[] a,int ind){
            a[ind]=x;
        }
    }

    public static class B{
        float x;
        public void start(){ x=0;}
        public void dec(){x--;}
        public void decl(float len){x-=len;}
        public void place(float[] a,int ind){
            a[ind]=x;
        }
    }

    public static void testA(){
        Random r=new Random();
        int initCount=Thread.activeCount();
        GenericActiveObject gao=new GenericActiveObject(new A());
        if(Thread.activeCount()!=initCount)        
            System.out.println("your active object should not open a new thread yet (-5)");
        
        gao.execute("init");
        if(Thread.activeCount()!=initCount+1)
            System.out.println("your active object did not open a new thread when it should have (-5)");
        gao.execute("inc");        
        int l=r.nextInt(100);
        gao.execute("incl",l);
        int ind=r.nextInt(10);
        int ar[]=new int[10];
        gao.execute("set", ar,ind);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}

        if(ar[ind]!=l+1)
            System.out.println("your active object did not perform the actions (-10)");

        
        gao.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        if(Thread.activeCount()!=initCount)        
            System.out.println("your active object did not close its thread (-5)");
    }

    public static void testB(){
        Random r=new Random();
        int initCount=Thread.activeCount();
        GenericActiveObject gao=new GenericActiveObject(new B());
        if(Thread.activeCount()!=initCount)        
            System.out.println("your active object should not open a new thread yet (-5)");
        
        gao.execute("start");
        if(Thread.activeCount()!=initCount+1)
            System.out.println("your active object did not open a new thread when it should have (-5)");
        gao.execute("dec");        
        int l=r.nextInt(100);
        gao.execute("decl",l);
        int ind=r.nextInt(10);
        float ar[]=new float[10];
        gao.execute("place", ar,ind);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}

        if(ar[ind]!=-l-1)
            System.out.println("your active object did not perform the actions (-10)");

        
        gao.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        if(Thread.activeCount()!=initCount)        
            System.out.println("your active object did not close its thread (-5)");
    }


    public static void main(String[] args) {
        try{
            if(Math.random()>0.5)
                testA();
            else
                testB();
        }catch(Exception e){
            System.out.println("your code threw an exception (-25)");
        }
        System.out.println("done");
    }
}
