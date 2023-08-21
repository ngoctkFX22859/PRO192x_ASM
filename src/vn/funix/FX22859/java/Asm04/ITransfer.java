package vn.funix.FX22859.java.Asm04;

import vn.funix.FX22859.java.Asm02.Account;

//xử lý nghiệp vụ chuyển tiền, bao gồm phương thức transfer(receiveAccount, amount)
public interface ITransfer {
    boolean transfers(Account receiveAccount, double amount);
}
