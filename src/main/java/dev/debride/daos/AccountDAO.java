package dev.debride.daos;

import dev.debride.entities.Account;

import java.util.Set;

public interface AccountDAO {

    // CREATE

    Account registerAccount(Account account);

    // READ
    Account getAccountById(int cid, int id);
    Set<Account> getAccounts(int id);
    Set<Account> accountsGreater(int id, int x);
    Set<Account> accountsLess(int id, int y);
    Set<Account> accountsGreatAndLess(int id, int x, int y);

    // UPDATE

    Account updateAccount(Account account);

    // DELETE

    boolean deleteAccount(int id);

}
