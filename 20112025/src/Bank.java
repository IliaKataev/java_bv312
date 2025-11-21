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

    //executeOperation
    //depositToAccount
    //withdrawFromAccount
    //transfer
    //printAccounts
    //printOperations
}

