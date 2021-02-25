package dev.debride.services;

import dev.debride.daos.AccountDAO;
import dev.debride.entities.Account;

import java.util.Set;
import java.util.logging.Logger;

public class AccountServiceImpl implements AccountService{

    private static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());
    private AccountDAO adao;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.adao = accountDAO;
    }

    @Override
    public Account registerAccount(Account account) {
        account.setBalance(0);
        this.adao.registerAccount(account);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {

        this.adao.updateAccount(account);

        return account;
    }

    @Override
    public Set<Account> getAccounts(int id) {
        return this.adao.getAccounts(id);
    }

    @Override
    public Set<Account> accountsGreater(int id, int x) {
        return this.adao.accountsGreater(id, x);
    }

    @Override
    public Set<Account> accountsLess(int id, int y) {
        return this.adao.accountsLess(id, y);
    }

    @Override
    public Set<Account> accountsGreatAndLess(int id, int x, int y) {
        return this.adao.accountsGreatAndLess(id, x, y);
    }

    @Override
    public Account getAccountById(int cid, int id) {
        return this.adao.getAccountById(cid, id);
    }


    @Override
    public boolean deleteAccount(int id) {
        return this.adao.deleteAccount(id);
    }


}
