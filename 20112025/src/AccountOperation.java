import java.math.BigDecimal;
import java.util.Objects;

public class AccountOperation extends Operation
{


    private final long accountId;
    private final Type type;

    protected AccountOperation(BigDecimal amount, long accountId, Type type) {
        super(amount);
        this.accountId = accountId;
        this.type = Objects.requireNonNull(type);
    }
    @Override
    public void doWork(Bank bank) throws OperationException {
        //bank
    }

    @Override
    public String toString(){
        return super.toString() + String.format("[%s] account=%d", type, accountId);
    }
}
enum Type {DEPOSIT, WITHDRAW}