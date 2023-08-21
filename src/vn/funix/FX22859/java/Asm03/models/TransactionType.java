package vn.funix.FX22859.java.Asm03.models;

public enum TransactionType {
    WITHDRAW("WITHDRAW"), DEPOSIT("DEPOSIT"), TRANSFER("TRANSFER");

    private String displayName;

    private TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
