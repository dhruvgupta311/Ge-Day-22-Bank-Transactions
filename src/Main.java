
class Customer extends Thread {
    private final Bank bank;
    private final int accountNumber;
    private final boolean isDeposit;
    private final int amount;

    // Constructor for the Customer class
    public Customer(Bank bank, int accountNumber, boolean isDeposit, int amount) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isDeposit) {
            bank.deposit(accountNumber, amount);
        } else {
            bank.withdraw(accountNumber, amount);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a Bank object with 5 accounts
        Bank bank = new Bank(5);

        // Create customers performing concurrent transactions
        Customer customer1 = new Customer(bank, 0, true, 100);  // Deposit 100 to account 0
        Customer customer2 = new Customer(bank, 1, true, 200);  // Deposit 200 to account 1
        Customer customer3 = new Customer(bank, 0, false, 50);  // Withdraw 50 from account 0
        Customer customer4 = new Customer(bank, 2, true, 300);  // Deposit 300 to account 2
        Customer customer5 = new Customer(bank, 1, false, 100); // Withdraw 100 from account 1

        // Start the transactions
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        customer5.start();

        // Wait for all customers to finish
        try {
            customer1.join();
            customer2.join();
            customer3.join();
            customer4.join();
            customer5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final account balances
        System.out.println("Final balance of account 0: " + bank.getBalance(0));
        System.out.println("Final balance of account 1: " + bank.getBalance(1));
        System.out.println("Final balance of account 2: " + bank.getBalance(2));
    }
}
