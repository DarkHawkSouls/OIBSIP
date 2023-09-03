import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private int userId;
    private int userPin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(int userId, int userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean checkCredentials(int enteredUserId, int enteredUserPin) {
        return userId == enteredUserId && userPin == enteredUserPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
        }
    }

    public void transfer(Account receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

public class ATM{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userId = 12345; // Sample user ID
        int userPin = 6789; // Sample user PIN
        Account account = new Account(userId, userPin);

        System.out.println("Welcome to the ATM System");
        System.out.print("Enter User ID: ");
        int enteredUserId = scanner.nextInt();
        System.out.print("Enter User PIN: ");
        int enteredUserPin = scanner.nextInt();

        if (account.checkCredentials(enteredUserId, enteredUserPin)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Transaction History");
                System.out.println("6. Quit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Balance: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter the amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        System.out.println("Deposit successful.");
                        break;
                    case 3:
                        System.out.print("Enter the amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;
                    case 4:
                        System.out.print("Enter the recipient's User ID: ");
                        int recipientUserId = scanner.nextInt();
                        System.out.print("Enter the amount to transfer: $");
                        double transferAmount = scanner.nextDouble();

                        if (recipientUserId == userId) {
                            System.out.println("Cannot transfer to your own account.");
                        } else {
                            // In a real ATM system, you would validate recipient's account, but here, we assume one account.
                            account.transfer(account, transferAmount);
                            System.out.println("Transfer successful.");
                        }
                        break;
                    case 5:
                        System.out.println("Transaction History:");
                        List<Transaction> history = account.getTransactionHistory();
                        for (Transaction transaction : history) {
                            System.out.println(transaction.getType() + ": $" + transaction.getAmount());
                        }
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }

        scanner.close();
    }
}