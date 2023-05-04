package com.learntocode;

import java.util.Scanner;

public class LedgerScreen {
    private TransactionManager transactionManager;
    private Scanner scanner;

    public LedgerScreen(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        scanner = new Scanner(System.in);
    }

    public void show() {
        String choice;
        do {
            System.out.println("==== Ledger ====");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    showAllEntries();
                    break;
                case "D":
                    showDeposits();
                    break;
                case "P":
                    showPayments();
                    break;
                case "R":
                    showReports();
                    break;
                case "H":
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (!choice.equalsIgnoreCase("H"));
    }

    private void showAllEntries() {
        System.out.println("==== All ====");

        for (Transaction transaction : transactionManager.getAllTransactions()) {
            System.out.println(transaction);
        }
    }

    private void showDeposits() {
        System.out.println("==== Deposits ====");

        for (Transaction transaction : transactionManager.getDeposits()) {
            System.out.println(transaction);
        }
    }

    private void showPayments() {
        System.out.println("==== Payments ====");

        for (Transaction transaction : transactionManager.getPayments()) {
            System.out.println(transaction);
        }
    }

    private void showReports() {
        String choice;
        do {
            System.out.println("==== Reports ====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showReport(transactionManager.getMonthToDateReport());
                    break;
                case "2":
                    showReport(transactionManager.getPreviousMonthReport());
                    break;
                case "3":
                    showReport(transactionManager.getYearToDateReport());
                    break;
                case "4":
                    showReport(transactionManager.getPreviousYearReport());
                    break;
                case "5":
                    System.out.print("Vendor name: ");
                    String vendor = scanner.nextLine();
                    showReport(transactionManager.getTransactionsByVendorReport(vendor));
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (!choice.equals("0"));
    }

    private void showReport(Transaction[] transactions) {
        if (transactions.length == 0) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

