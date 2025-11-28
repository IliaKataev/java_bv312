public class SyncResource {
    long counter;
    public synchronized void add(long value){
        this.counter += value;
    }
    public synchronized long getCounter(){
        return this.counter;
    }
}
