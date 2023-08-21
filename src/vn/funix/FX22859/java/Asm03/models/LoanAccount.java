package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm03.ReportService;
import vn.funix.FX22859.java.Asm03.Utils;
import vn.funix.FX22859.java.Asm03.Withdraw;

import java.text.DecimalFormat;

public class LoanAccount extends Account implements Withdraw, ReportService {
    private final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;

    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withDraw(double amount) {
        double newBalance;
        if (isAccepted(amount)) {
            newBalance = getBalance() - (amount + (amount * getTransactionFee()));
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount <= LOAN_ACCOUNT_MAX_BALANCE && minBalance(getBalance() - amount)) {
            return true;
        } else {
            if (amount > LOAN_ACCOUNT_MAX_BALANCE) {
                System.out.println("Số tiền đã vượt qua hạn mức 100.000.000đ. Giao dịch không thành công!");
            }
            if (!minBalance(getBalance() - amount)) {
                System.out.println("Số dư sau khi rút không thể dưới 50.000đ. Giao dịch không thành công!");
            }
        }
        return false;
    }

    public double getTransactionFee() {
        if (!isPremium()) {
            return LOAN_ACCOUNT_WITHDRAW_FEE;
        } else {
            return LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
        }
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitle() + "LOAN");
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(amount * getTransactionFee()));
        System.out.println(Utils.getDivider());
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
        String accountType = "LOANS";
        return " " + accountNumber + "            " + accountType + "     " + decimalFormat.format(balance);
    }
}
