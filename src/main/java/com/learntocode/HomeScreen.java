package com.learntocode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class HomeScreen {
    private TransactionManager transactionManager;
    private Scanner scanner;

    public HomeScreen(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        scanner = new Scanner(System.in);
    }

    public void show() {
        String choice;
        do {
            System.out.println("==== Home Screen ====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    showLedger();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (!choice.equalsIgnoreCase("X"));
    }

    private void addDeposit() {
        try {
            String Filepath = "transaction.csv";
            FileWriter file = new FileWriter(Filepath, true);
            BufferedWriter buffer = new BufferedWriter(file);


        System.out.println("==== Add Deposit ====");
        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Time (hh:mm:ss): ");
        String time = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        transactionManager.addDeposit(date, time, description, vendor, amount);
        buffer.write(date + "|" + time +"|" + description + "|" + vendor + "|" + amount + "\n");
        System.out.println("Deposit added successfully!");
        buffer.newLine();
        buffer.close();
          }catch (java.io.IOException e){
        System.out.println();
        }

    }

    private void makePayment() {
        System.out.println("==== Make Payment (Debit) ====");
        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Time (hh:mm:ss): ");
        String time = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        transactionManager.makePayment(date, time, description, vendor, amount);
        System.out.println("Payment made successfully!");
    }

    private void showLedger() {
        LedgerScreen ledgerScreen = new LedgerScreen(transactionManager);
        ledgerScreen.show();
    }
}
