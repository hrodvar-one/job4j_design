package ru.job4j.ood.lsp;

public class AccountMain {
    public static void main(String[] args) {
        BaseAccount account = new LimitedAccount(100, 50);
        account.withdraw(60);
        System.out.println("Balance: " + account.getBalance());
    }
}
