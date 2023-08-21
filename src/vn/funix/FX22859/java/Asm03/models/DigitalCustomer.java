package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.ReportService;
import vn.funix.FX22859.java.Asm03.Withdraw;

import java.io.Serializable;

public class DigitalCustomer extends Customer implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    public DigitalCustomer(String name, String customerID) {
        super(name, customerID);
    }

    public Account getAccountBy(String accNumber) {
        for (Account acc : getAccounts()) {
            if (acc.getAccountNumber().equals(accNumber)) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public void displayInformation() {
        String isPre = isPremium() ? "Premium" : "Normal";
        System.out.println(getCustomerID() + "  " + getName() + "  " + isPre + "   " + getBalance());

        int index = 1;
        for (Account account : accounts) {
            System.out.println(index + "  " + account.toString());
            index++;
        }
    }

    public void withdraw(String accountNumber, double amount) {
        boolean status;
        Account acc = this.getAccountBy(accountNumber);
        if (acc != null) {
            if (acc instanceof Withdraw) { // Kiểm tra acc có là một đối tượng lớp con của Withdraw
                Withdraw wd = (Withdraw) acc; //downcasting
                status = wd.withDraw(amount);
                acc.addTransaction(accountNumber, -amount, status);
                if (status) {
                    if (acc instanceof ReportService) {
                        ReportService rp = (ReportService) acc;
                        rp.log(amount);
                    }
                }
            }
        }
    }
}
