package dev.debride.daos;

import dev.debride.entities.Account;
import dev.debride.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoPostgre implements AccountDAO{
    @Override
    public Account registerAccount(Account account) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into account (balance, owner_id) values (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getOwnerId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("account_id");
            account.setAccountId(key);

            return account;



        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return null;
        }

    }

    @Override
    public Account getAccountById(int cid, int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from account where owner_id = ? and account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Account account = new Account();
            account.setAccountId(rs.getInt("account_id"));
            account.setBalance(rs.getDouble("balance"));
            account.setOwnerId(rs.getInt("owner_id"));

            return account;


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> getAccounts(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from account where owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Set<Account> accounts = new HashSet<Account>();

            while(rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOwnerId(rs.getInt("owner_id"));
                accounts.add(account);
            }
            return accounts;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> accountsGreater(int id, int x) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "select * from account where balance >= ? and owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, x);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();

            Set<Account> accounts = new HashSet<Account>();

            while(rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOwnerId(rs.getInt("owner_id"));
                accounts.add(account);
            }
            return accounts;


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> accountsLess(int id, int y) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "select * from account where balance <= ? and owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, y);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();

            Set<Account> accounts = new HashSet<Account>();

            while(rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOwnerId(rs.getInt("owner_id"));
                accounts.add(account);
            }
            return accounts;


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> accountsGreatAndLess(int id, int x, int y) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "select * from account where balance >= ? and balance <= ? and owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();

            Set<Account> accounts = new HashSet<Account>();

            while(rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOwnerId(rs.getInt("owner_id"));
                accounts.add(account);
            }
            return accounts;


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Account updateAccount(Account account) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update account set balance = balance + ? where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getAccountId());
            ps.executeUpdate();
            return account;


        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAccount(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from account where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }

    }
}
