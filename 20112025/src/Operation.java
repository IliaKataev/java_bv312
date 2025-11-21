import java.math.BigDecimal;
import java.util.Date;

abstract class Operation {
    protected final BigDecimal amount;
    protected final Date timestamp = new Date();

    protected Operation(BigDecimal amount){
        this.amount = amount == null ? BigDecimal.ZERO : amount;
    }

    public BigDecimal getAmount(){ return amount; }

    public Date getTimestamp() { return timestamp; }

    public abstract void doWork(Bank bank) throws OperationException;

    @Override
    public String toString(){
        return String.format("[%tF %tT] %s amount=%s", timestamp, timestamp, getClass().getSimpleName(), amount.toPlainString());
    }
}
