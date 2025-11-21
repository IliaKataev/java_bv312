import java.math.BigDecimal;
import java.util.*;
import java.lang.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("СберБанк");

        Client clientIlia = bank.getOrCreateClient("C001", "Ilia");
        Client clientDelice = bank.getOrCreateClient("C002", "Delice");

        Account a1 = bank.createAccount(clientIlia, new BigDecimal("1.00"));
        Account a2 = bank.createAccount(clientDelice, new BigDecimal("2.00"));

        System.out.println("Initial Balance:");
        bank.printAccount();

        AccountOperation deposit = new AccountOperation(new BigDecimal("250.00"), a1.getId(), Type.DEPOSIT);
        try{
            bank.executeOperation(deposit);
        } catch (OperationException e) {
            ErrorLogger.getInstance().log("Deposit failed: " + e.getMessage());
        }

        AccountOperation withdraw = new AccountOperation(new BigDecimal("500.00"), a2.getId(), Type.WITHDRAW);
        try{
            bank.executeOperation(withdraw);
        } catch (OperationException e) {
            ErrorLogger.getInstance().log("Withdraw failed: " + e.getMessage());
        }

        AccountOperation withdraw1 = new AccountOperation(new BigDecimal("9999.00"), a2.getId(), Type.WITHDRAW);
        try{
            bank.executeOperation(withdraw1);
        } catch (OperationException e) {
            ErrorLogger.getInstance().log("Withdraw failed: " + e.getMessage());
        }

        System.out.println("Balance after operations:");
        bank.printAccount();

        CrossAccountOperation transfer = new CrossAccountOperation(a1.getId(), a2.getId(), new BigDecimal("500.00"));
        try{
            bank.executeOperation(transfer);
        } catch (OperationException e) {
            ErrorLogger.getInstance().log("Transfer failed: " + e.getMessage());
        }

        System.out.println("Balance after transfer:");
        bank.printAccount();

        System.out.println("\nAll recorded operations: ");
        bank.printOperations();

        System.out.println("\nERROR LOG: ");
        ErrorLogger.getInstance().printErrors();
    }
}