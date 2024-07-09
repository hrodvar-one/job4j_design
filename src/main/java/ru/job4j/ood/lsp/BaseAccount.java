package ru.job4j.ood.lsp;

public class BaseAccount {
    protected double balance;

    public BaseAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
