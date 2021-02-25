package dev.debride.entities;

public class Account {
    private int accountId;
    private double balance;
    private int ownerId;

    public Account(){}

    public Account(int accountId, double amount, int ownerId) {
        this.accountId = accountId;
        this.balance = amount;
        this.ownerId = ownerId;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", ownerId=" + ownerId +
                '}';
    }
}
