import java.math.BigDecimal;

public class CrossAccountOperation extends Operation {
    private final long fromAccountId;
    private final long toAccountId;

    protected CrossAccountOperation(long fromAccountId, long toAccountId, BigDecimal amount){
        super(amount);
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;

    }

    @Override
    public void doWork(Bank bank) throws OperationException{
        //bank
    }

    @Override
    public String toString(){
        return String.format("[TRANSFER from=%d to=%d", fromAccountId, toAccountId);
    }
}
