import java.util.*;

public class ErrorLogger {
    private static final ErrorLogger INSTANCE = new ErrorLogger();
    private final List<BankError> errors = new ArrayList<>();
    private List<String> listStrings = new LinkedList<>();
    public Set<String> uniqueItems  = new HashSet<>();
    // listStrings( A -> <- B -> <-C-> <-D-> <-E )

    private ErrorLogger() {}

    public static ErrorLogger getInstance(){
        return INSTANCE;
    }

    public synchronized void log(String message){
        errors.add(new BankError(message));
    }

    public List<BankError> getErrors(){
        return Collections.unmodifiableList(errors);
    }

    public void printErrors(){
        errors.forEach(System.out::println);
    }
}

//list arraylist linkedlist
