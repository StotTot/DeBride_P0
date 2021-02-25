package dev.debride.daotests;


import dev.debride.daos.AccountDAO;
import dev.debride.daos.AccountDaoPostgre;
import dev.debride.entities.Account;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDaoTest {



    private static AccountDAO adao = new AccountDaoPostgre();
    private static Account testAccount = null;



    @Test
    @Order(1)
    void create_account(){
        Account johnSmith = new Account(0, 0, 15);
        adao.registerAccount(johnSmith);
        System.out.println(johnSmith);
        testAccount = johnSmith;
        Assertions.assertNotEquals(0, johnSmith.getAccountId());
        Assertions.assertEquals(0, johnSmith.getBalance());

    }

    @Test
    @Order(2)
    void get_account_by_id(){
        System.out.println(testAccount.getAccountId());
        int cid = 15;
        int id = testAccount.getAccountId();
        Account account = adao.getAccountById(cid, id);
        System.out.println(adao.getAccountById(cid, id));
        Assertions.assertEquals(testAccount.getAccountId(), account.getAccountId());
        Assertions.assertEquals(testAccount.getBalance(), account.getBalance());
        System.out.println(account);


    }

    @Test
    @Order(3)
    void get_accounts(){
        int id = 15;
        Set<Account> allAccounts = adao.getAccounts(id);
        Assertions.assertTrue(allAccounts.size() >= 1);
        System.out.println(allAccounts);

    }

    @Test
    @Order(4)
    void update_account(){
        Account account = adao.getAccountById(15, testAccount.getAccountId());
        account.setBalance(5000);
        adao.updateAccount(account);

        Account updatedAccount = adao.getAccountById(15, testAccount.getAccountId());
        Assertions.assertEquals(5000, updatedAccount.getBalance());
        System.out.println(updatedAccount);
    }
    @Test
    @Order(5)
    void get_accounts_greater(){
        int id = 15;
        Set<Account> allAccounts = adao.accountsGreater(id, 400);
        Assertions.assertTrue(allAccounts.size() >= 1);
        System.out.println(allAccounts);
    }

    @Test
    @Order(6)
    void get_accounts_less(){
        int id = 15;
        Set<Account> allAccounts = adao.accountsLess(id, 200000000);
        Assertions.assertTrue(allAccounts.size() >= 1);
        System.out.println(allAccounts);
    }


    @Test
    @Order(7)
    void get_accounts_greater_and_less(){

        Set<Account> allAccounts = adao.accountsGreatAndLess(15, 400, 2000000 );
        Assertions.assertTrue(allAccounts.size() >= 1);
        System.out.println(allAccounts);
    }


    @Test
    @Order(8)
    void delete_account_by_id(){
        int id = testAccount.getAccountId();
        boolean result = adao.deleteAccount(id);
        Assertions.assertTrue(result);
    }







}
