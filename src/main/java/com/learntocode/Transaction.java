package com.learntocode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {

        this.time = time;
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String toString() {
        return "transaction" + date + "|" + time +"|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<Transaction>();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String description = "Test transaction";
        String vendor = "Test vendor";
        double amount = 100.00;

        transactions.add(new Transaction(date, time, description, vendor, amount));

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

