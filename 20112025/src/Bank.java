import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {
    private final String name;
    private final Map<String, Client> clients = new HashMap<>();
    private final Map<Long, Account> accounts = new HashMap<>();
    private final List<Operation> operations = new ArrayList<>();
    private final AtomicLong accountIdGenerator = new AtomicLong();

    public Bank(String name){
        this.name = name;
    }

    public Client getOrCreateClient(String clientId, String fullName){
        return clients.computeIfAbsent(clientId, id -> new Client(id, fullName));
    }

    public Account createAccount(Client client, BigDecimal initialBalance){
        long id = accountIdGenerator.getAndIncrement();
        Account acc = Account.createForBank(id, client, initialBalance);
        accounts.put(acc.getId(), acc);
        return acc;
    }

    public Optional<Account> findAccount(long accountId){
        return Optional.ofNullable(accounts.get(accountId)); //Account or Optional.Empty()
    }

    public void executeOperation(Operation op) throws OperationException{
        op.doWork(this);
        operations.add(op);
    }

    void depositToAccount(long accountId, BigDecimal amount) throws OperationException {
        Account account = accounts.get(accountId);
        if(account == null) throw new OperationException("Account not found");
        account.deposit(amount);
    }
    void withdrawFromAccount(long accountId, BigDecimal amount) throws OperationException {
        Account account = accounts.get(accountId);
        if(account == null) throw new OperationException("Account not found");
        account.withdraw(amount);
    }

    void transfer(long fromId, long toId, BigDecimal amount) throws OperationException{
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);
        if(from == null) throw new OperationException("Source was not found");
        if(to == null) throw new OperationException("Target was not found");

        Account firstLock = fromId < toId ? from : to;
        Account secondLock = fromId < toId ? to : from;

        //A - a -> b
        //B - b -> a

        // Поток1  - захватывает счет А
        // Поток2 захватывает счет Б
        // Поток1 - попытка захвата счета Б - но он занят
        // Поток2 попытка захвата счета А - но он занят

        synchronized (firstLock){
            synchronized (secondLock){
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public void printAccount(){
        for(Account a : accounts.values()){
            System.out.printf("Account %d | Owner %s | Balance %s%n", a.getId(), a.getOwner().getFullName(), a.getBalance().toPlainString());
        }
    }

    public void printOperations(){
        for (Operation o : operations){
            System.out.println(o);
        }
    }
    //executeOperation
    //depositToAccount
    //withdrawFromAccount
    //transfer
    //printAccounts
    //printOperations
}

