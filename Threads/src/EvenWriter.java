import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EvenWriter implements Runnable{

    private final List<Integer> data;
    private final String outFile;
    private final AtomicInteger counter;

    public EvenWriter(List<Integer> data, String outFile, AtomicInteger counter) {
        this.data = data;
        this.outFile = outFile;
        this.counter = counter;
    }


    @Override
    public void run() {
        try(PrintWriter pw = new PrintWriter(new FileWriter(outFile))){
            for(int n: data){
                if(n % 2 == 0){
                    pw.println(n);
                    counter.incrementAndGet();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
