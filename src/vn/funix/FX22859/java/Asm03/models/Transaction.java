package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm03.Utils;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;


    private TransactionType type = TransactionType.WITHDRAW;

    public Transaction(String accountNumber, double amount, boolean status) {
        this.id = String.valueOf(UUID.randomUUID()).substring(0, 6);
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = Utils.getDateTime();
        this.status = status;
    }

    public Transaction(String accountNumber, double amount, boolean status, TransactionType type) {
        this.id = String.valueOf(UUID.randomUUID()).substring(0, 6);
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = Utils.getDateTime();
        this.status = status;
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public void displayTransaction() {
        String amountString = "";
        switch (type) {
            case TRANSFER, WITHDRAW:
                amountString = "-" + Utils.formatBalance(this.getAmount());
                break;
            case DEPOSIT:
                amountString = "+" + Utils.formatBalance(this.getAmount());
                break;
            default:
                break;
        }
        System.out.println(Utils.getDivider());
        System.out.println("[GD]  " + accountNumber + " | " + type.getDisplayName() + " | " + amountString + "   |  " + this.getTime());
    }
}
