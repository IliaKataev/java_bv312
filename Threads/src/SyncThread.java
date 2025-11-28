public class SyncThread  extends Thread{
    protected SyncResource counter = null;

    public SyncThread(SyncResource counter){
        this.counter = counter;
    }

    public void run(){
        for(int i = 0; i <= 100; i++){
            counter.add(i);
        }
    }
}
