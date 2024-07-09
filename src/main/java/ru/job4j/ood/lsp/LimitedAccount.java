package ru.job4j.ood.lsp;

public class LimitedAccount extends BaseAccount {
    private double limit;

    public LimitedAccount(double balance, double limit) {
        super(balance);
        this.limit = limit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance && amount <= limit) {
            balance -= amount;
        }
    }
}
