import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleBankingApp {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Simple Banking Application");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    bank.createAccount(name);
                    System.out.println("Account created successfully.");
                    break;
                case 2:
                    System.out.print("Enter your account number: ");
                    int accountNumber = scanner.nextInt();
                    System.out.print("Enter the deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(accountNumber, depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter your account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.print("Enter the withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    if (bank.withdraw(accountNumber, withdrawalAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance or invalid account number.");
                    }
                    break;
                case 4:
                    System.out.print("Enter your account number: ");
                    accountNumber = scanner.nextInt();
                    double balance = bank.checkBalance(accountNumber);
                    if (balance >= 0) {
                        System.out.println("Your balance: " + balance);
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Bank {
    private Map<Integer, Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        accounts = new HashMap<>();
        nextAccountNumber = 1;
    }

    public void createAccount(String name) {
        int accountNumber = nextAccountNumber++;
        Account account = new Account(accountNumber, name);
        accounts.put(accountNumber, account);
    }

    public void deposit(int accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            account.deposit(amount);
        }
    }

    public boolean withdraw(int accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            return account.withdraw(amount);
        }
        return false;
    }

    public double checkBalance(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.get(accountNumber);
            return account.getBalance();
        }
        return -1; // Invalid account number
    }
}

class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public Account(int accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}
