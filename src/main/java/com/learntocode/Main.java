package com.learntocode;

public class Main {
    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();
        LedgerScreen ledgerScreen = new LedgerScreen(transactionManager);
        HomeScreen homeScreen = new HomeScreen(transactionManager);

        homeScreen.show();
    }
}