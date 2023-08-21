package vn.funix.FX22859.java.Asm04.exception;

import vn.funix.FX22859.java.Asm02.User;

public class CustomerIdNotValidException extends Exception {
    public CustomerIdNotValidException(String message) {
        super(message);
    }

    public static void isCustomerIdValid(String customerId) throws CustomerIdNotValidException {
        if (customerId.length() != 12 || !User.isFirstThreeDigitsValid(customerId)
                || customerId.matches(".*[a-zA-Z].*")) {
            throw new CustomerIdNotValidException("CCCD không hợp lệ.");
        }
    }
}
