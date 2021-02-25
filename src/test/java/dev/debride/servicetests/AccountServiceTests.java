package dev.debride.servicetests;



import dev.debride.daos.AccountDaoPostgre;
import dev.debride.entities.Account;
import dev.debride.services.AccountService;
import dev.debride.services.AccountServiceImpl;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTests {

    private static AccountService aserv = new AccountServiceImpl(new AccountDaoPostgre());
    private static Account testAccount = null;

    @Test
    @Order(1)
    void registerAccount(){

        Account account = new Account(0, 0,15);
        aserv.registerAccount(account);
        System.out.println(account);

        Assertions.assertNotEquals(0, account.getAccountId());
        Assertions.assertEquals(15, account.getOwnerId());
        Assertions.assertEquals(0, account.getBalance());

        testAccount = account;
    }

    @Test
    @Order(2)
    void update_account_balance(){
        Account account = new Account(testAccount.getAccountId(), 500, testAccount.getOwnerId());
        aserv.updateAccount(account);

        System.out.println(account);
        Assertions.assertEquals(500, account.getBalance());


    }
}
