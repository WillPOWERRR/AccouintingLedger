package com.learntocode;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionManager {
    private ArrayList<Transaction> transactions;
    private static final String FILENAME = "transaction.csv";
    public TransactionManager() {
        transactions = new ArrayList<>();
        loadTransactions();
    }

    public void addDeposit(String date, String time, String description, String vendor, double amount) {
        LocalTime time1 = LocalTime.parse(time);
        LocalDate date1 = LocalDate.parse(date);
        transactions.add(new Transaction(date1, time1, description, vendor, amount));
    }


    public void makePayment(String date, String time, String description, String vendor, double amount) {
        LocalDate date2 = LocalDate.parse(date);
        LocalTime time2 = LocalTime.parse(time);
        Transaction transaction = new Transaction(date2, time2, description, vendor, -amount);
        transactions.add(transaction);
        saveTransactions();
    }

    public Transaction[] getAllTransactions() {
        Transaction[] allTransactions = new Transaction[transactions.size()];
        return transactions.toArray(allTransactions);
    }

    public Transaction[] getDeposits() {
        ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        Transaction[] depositArray = new Transaction[deposits.size()];
        return deposits.toArray(depositArray);
    }

    public Transaction[] getPayments() {
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }
        Transaction[] paymentArray = new Transaction[payments.size()];
        return payments.toArray(paymentArray);
    }

    public Transaction[] getMonthToDateReport() {
        return getReport(LocalDate.now().withDayOfMonth(1), LocalDate.now());
    }

    public Transaction[] getPreviousMonthReport() {
        LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().minusMonths(1).withDayOfMonth(startDate.lengthOfMonth());
        return getReport(startDate, endDate);
    }

    public Transaction[] getYearToDateReport() {
        return getReport(LocalDate.now().withDayOfYear(1), LocalDate.now());
    }

    public Transaction[] getPreviousYearReport() {
        LocalDate startDate = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endDate = LocalDate.now().minusYears(1).withDayOfYear(startDate.lengthOfYear());
        return getReport(startDate, endDate);
    }

    public Transaction[] getTransactionsByVendorReport(String vendor) {
        ArrayList<Transaction> matchingTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                matchingTransactions.add(transaction);
            }
        }
        Transaction[] matchingTransactionsArray = new Transaction[matchingTransactions.size()];
        return matchingTransactions.toArray(matchingTransactionsArray);
    }

    private Transaction[] getReport(LocalDate startDate, LocalDate endDate) {
        ArrayList<Transaction> matchingTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                matchingTransactions.add(transaction);
            }
        }
        Transaction[] matchingTransactionsArray = new Transaction[matchingTransactions.size()];
        return matchingTransactions.toArray(matchingTransactionsArray);
    }

    private void loadTransactions() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            } catch (IOException e) {
                System.out.println("Error reading transactions file.");
            }
        }
    }

    private void saveTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Transaction transaction : transactions) {
                writer.println(transaction);
            }
        } catch (IOException e) {
            System.out.println("Error writing to transactions file.");
        }
    }
}
