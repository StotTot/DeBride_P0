package dev.debride.services;

import dev.debride.entities.Account;

import java.util.Set;

public interface AccountService {

    Account registerAccount(Account account);

    Set<Account> getAccounts(int id);
    Set<Account> accountsGreater(int client_id, int x);
    Set<Account> accountsLess(int client_id, int y);
    Set<Account> accountsGreatAndLess(int client_id, int x, int y);
    Account getAccountById(int cid, int id);

    Account updateAccount(Account account);


    boolean deleteAccount(int id);

}
