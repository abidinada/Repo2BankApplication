
import com.imt.mines.BankAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testDepositMoney() {
        BankAccount acc = new BankAccount();
        acc.depositMoney(100);
        assertEquals(100, acc.getBalance());
    }

    @Test
    void testWithdrawTooMuch() {
        BankAccount acc = new BankAccount();
        acc.depositMoney(50);

        boolean result = acc.withdrawMoney(100);

        assertFalse(result);
        assertEquals(50, acc.getBalance());
    }
    @Test
    void testDepositNegativeAmount() {
        BankAccount acc = new BankAccount();
        acc.depositMoney(100);   // solde = 100

        acc.depositMoney(-50);   // dépôt négatif → ignoré

        assertEquals(100, acc.getBalance()); // solde reste inchangé
    }

    @Test
    void testCumulativeWithdrawExceedsLimit() {
        BankAccount acc = new BankAccount();
        acc.depositMoney(500);
        acc.setWithdrawLimit(100); // limite totale

        boolean first = acc.withdrawMoney(60);
        boolean second = acc.withdrawMoney(50); // doit échouer (60 + 50 > 100)

        assertTrue(first);
        assertFalse(second);
        assertEquals(500 - 60, acc.getBalance()); // solde reste après 1er retrait
    }
}
