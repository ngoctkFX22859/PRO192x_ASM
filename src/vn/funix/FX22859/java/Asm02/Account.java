package vn.funix.FX22859.java.Asm02;

import vn.funix.FX22859.java.Asm03.models.Transaction;
import vn.funix.FX22859.java.Asm03.models.TransactionType;
import vn.funix.FX22859.java.Asm04.dao.CustomerDao;
import vn.funix.FX22859.java.Asm04.dao.TransactionDao;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    protected String accountNumber;
    protected double balance;
    private List<Transaction> transactions;
    private String customerId;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public Account(String customerId, String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(String accountNumber, double amount, boolean status) {
        this.transactions.add(new Transaction(accountNumber, amount, status));
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static boolean minBalance(double balance) {
        return balance >= 50000;
    }

    public boolean isPremium() {
        return getBalance() >= 10000000;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
        return " " + accountNumber + "                         " + decimalFormat.format(balance);
    }

    public void transactionInfo() {
        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    public void createTransaction(double amount, boolean status, TransactionType type) throws IOException {
        Transaction trans = new Transaction(accountNumber, amount, true, type);
        TransactionDao.addTransaction(trans);
    }

    public void displayTransactionsList() {
        List<Transaction> listTrans = TransactionDao.getTransactions(accountNumber);
        for (Transaction trans : listTrans) {
            trans.displayTransaction();
        }
    }
}
