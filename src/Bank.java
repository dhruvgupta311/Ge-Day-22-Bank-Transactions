class Bank {
    private final int[] accounts;

    // Constructor to initialize the bank with 'n' accounts.
    public Bank(int n) {
        accounts = new int[n];
    }

    // Synchronized deposit method to ensure thread safety.
    public synchronized void deposit(int accountNumber, int amount) {
        accounts[accountNumber] += amount;
        System.out.println("Deposited " + amount + " to account " + accountNumber);
    }

    // Synchronized withdraw method to ensure thread safety.
    public synchronized void withdraw(int accountNumber, int amount) {
        if (accounts[accountNumber] >= amount) {
            accounts[accountNumber] -= amount;
            System.out.println("Withdrew " + amount + " from account " + accountNumber);
        } else {
            System.out.println("Insufficient balance for withdrawal from account " + accountNumber);
        }
    }

    // Method to get the balance of a specific account.
    public int getBalance(int accountNumber) {
        return accounts[accountNumber];
    }
}
