import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchTask implements Runnable{

    private final String path;
    private final String word;
    private final AtomicInteger countResult;

    public SearchTask(String path, String word, AtomicInteger countResult) {
        this.path = path;
        this.word = word;
        this.countResult = countResult;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            while ((line = br.readLine()) != null){
                int index = 0;

                while((index = line.indexOf(word, index)) != -1){
                    countResult.incrementAndGet();
                    index += word.length();
                    // "hello world hello" 17 симв. - "hello"
                    // 1. "hello" 0 -> 0
                    // 2. "hello" 5 -> 12
                    // 3. "hello" 17 -> while == -1
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
