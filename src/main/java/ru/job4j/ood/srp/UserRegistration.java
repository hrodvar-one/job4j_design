package ru.job4j.ood.srp;

public class UserRegistration {
    private String username;
    private String password;
    private String email;

    public UserRegistration(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean validate() {
        return username != null && password != null && email != null && email.contains("@");
    }

    public void sendWelcomeEmail() {
    }
}
