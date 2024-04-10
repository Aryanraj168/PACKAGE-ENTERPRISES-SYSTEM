package EnterpriseSystem;


import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolderName;
    private int accountNumber;
    private double balance;

    public Account(String accountHolderName, int accountNumber, double balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance:- INR" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. Current balance:- INR" + balance);
            } else {
                System.out.println("Insufficient funds. Withdrawal failed.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }

    public void displayAccountDetails(boolean showBalance) {
        System.out.println("\nAccount Information:");
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);

        if (showBalance) {
            System.out.println("Current Balance:- INR" + balance);
        }
    }
}

class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create an Account");
            System.out.println("2. Display Account Details");
            System.out.println("3. Search by Account Number");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner, accounts);
                    break;
                case 2:
                    displayAccountDetails(scanner, accounts);
                    break;
                case 3:
                    searchByAccountNumber(scanner, accounts);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void createAccount(Scanner scanner, ArrayList<Account> accounts) {
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.next();

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Enter Initial Balance:- INR");
        double initialBalance = scanner.nextDouble();

        accounts.add(new Account(accountHolderName, accountNumber, initialBalance));
        System.out.println("Account created successfully!");
    }

    private static void displayAccountDetails(Scanner scanner, ArrayList<Account> accounts) {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.displayAccountDetails(true);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void searchByAccountNumber(Scanner scanner, ArrayList<Account> accounts) {
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account selectedAccount = null;
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                selectedAccount = account;
                break;
            }
        }

        if (selectedAccount == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\nSearch Options:");
        System.out.println("1. Deposit Amount");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Show Balance");

        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();

        switch (searchChoice) {
            case 1:
                System.out.print("Enter deposit amount:- INR");
                double depositAmount = scanner.nextDouble();
                selectedAccount.deposit(depositAmount);
                break;
            case 2:
                System.out.print("Enter withdrawal amount:- INR");
                double withdrawalAmount = scanner.nextDouble();
                selectedAccount.withdraw(withdrawalAmount);
                break;
            case 3:
                selectedAccount.displayAccountDetails(true);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}