import java.awt.image.AffineTransformOp;
import java.util.Random;

public class Main {
    //public static int counter = 0;
    public static Object locker = new Object();
    public static void main(String[] args) {
        int limit= 10000;
        SyncResource syncResource = new SyncResource();

        Thread t1 = new SyncThread(syncResource);
        Thread t2 = new SyncThread(syncResource);

        t1.start();
        t2.start();
//        IncThread incThread = new IncThread(limit);
//        DecThread decThread = new DecThread(limit);
//
//        incThread.start();
//        decThread.start();
//
        try{
            t1.join();
            t2.join();
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("counter="+syncResource.getCounter());
    }

    public static void BasicThreads(){
        //        MyThread t1 = new MyThread();
//        t1.setDaemon(true);
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t1.start();
//        int laps = 10000;
//        while(laps > 0){
//            System.out.println(".");
//            laps--;
//        }

        int v = (new Random()).nextInt(10);
        Thread3 t2 = new Thread3(v);
        Thread t22 = new Thread(t2);
        t22.setDaemon(true);
        v = (new Random()).nextInt(10);
        Thread3 t3 = new Thread3(v);
        Thread t33 = new Thread(t3);
        t33.setDaemon(true);
        System.out.println(t22.getName());
        t22.start();
        t33.start();
        t22.interrupt();

//        while(t2.isAlive() || t3.isAlive()){
//          Так делать не стоит
//        }

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException _){}

        try{
            t22.join(100);
            t33.join(5000);
        } catch(InterruptedException _){}

    }
}