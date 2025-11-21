import java.time.LocalDateTime;

public class BankError {
    private final LocalDateTime Date;
    private final String TextError;

    public BankError(String TextError){
        this.Date = LocalDateTime.now();
        this.TextError = TextError;
    }

    public LocalDateTime getDate(){
        return Date;
    }

    public String getMessage(){
        return TextError;
    }

    public String toString(){
        return String.format("%s Error: %s", Date.toString(), TextError);
    }
}
