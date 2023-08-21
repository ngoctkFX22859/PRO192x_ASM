package vn.funix.FX22859.java.Asm03.models;

import org.junit.jupiter.api.Test;
import vn.funix.FX22859.java.Asm02.Account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class SavingsAccountTest {
    private SavingsAccount sa;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        sa = new SavingsAccount("077000000001", "111111", 20000000);
    }

    @org.junit.jupiter.api.Test
    public void isAccepted() {
        assertFalse(sa.isAccepted(-50000));// False vì nhập số âm
        assertTrue(sa.isAccepted(50000));// True vì nhập ít nhất 50K và là bội của 10K, nhỏ hơn số dư hiện tại.
        assertFalse(sa.isAccepted(450001));// False vì ko chia hết cho 10K
        assertTrue(sa.isAccepted(5000000));// True vì chưa vượt hạn mưc tối đa 1 lần rút và khi rút ra  vẫn còn hơn 50K số dưu còn lại
    }

    @org.junit.jupiter.api.Test
    void withDraw() {
        assertFalse(sa.withDraw(-50000));// False vì nhập số âm
        assertTrue(sa.withDraw(50000));// True vì nhập ít nhất 50K và là bội của 10K, nhỏ hơn số dư hiện tại.
        assertFalse(sa.withDraw(450001));// False vì ko chia hết cho 10K
        assertTrue(sa.withDraw(5000000));// True vì chưa vượt hạn mưc tối đa 1 lần rút và khi rút ra  vẫn còn hơn 50K số dư còn lại
    }
    
    @org.junit.jupiter.api.Test
    void transfers() {
        Account senderAccount = new SavingsAccount("111111", 20000000);
        Account receiverAccount = new SavingsAccount("222222", 10000000);

        assertTrue(((SavingsAccount) senderAccount).transfers(receiverAccount, 200000));
        assertFalse(((SavingsAccount) senderAccount).transfers(receiverAccount, 20000000));
        assertFalse(((SavingsAccount) senderAccount).transfers(receiverAccount, -200000));
        assertTrue(((SavingsAccount) senderAccount).transfers(receiverAccount, 10000000));
    }
}