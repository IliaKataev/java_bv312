import java.math.BigDecimal;

class Account {
    private  final long id;
    private final Client owner;
    private BigDecimal balance;

    private Account(long id, Client owner, BigDecimal initialBalance){
        this.id = id;
        this.owner = owner;
        this.balance = initialBalance == null ? BigDecimal.ZERO : initialBalance;
    }

    static Account createForBank(long id, Client owner, BigDecimal initialBalance){
        return new Account(id, owner, initialBalance);
    }

    public long getId() {return id;}
    public Client getOwner(){return owner;}
    public synchronized BigDecimal getBalance(){return balance;}

    synchronized void deposit(BigDecimal amount) throws OperationException {
        if(amount == null || amount.signum() <= 0)
            throw new OperationException("Deposit must be > 0");
        balance = balance.add(amount);
    }

    synchronized void withdraw(BigDecimal amount) throws OperationException {
        if(amount == null || amount.signum() <= 0)
            throw new OperationException("Deposit must be > 0");
        if(balance.compareTo(amount) < 0)
            throw  new OperationException("Insufficient funds");
        balance = balance.subtract(amount);
    }
}
