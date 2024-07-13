package ru.job4j.ood.dip;

public class UserService {
    private ConsoleLogger logger;

    public UserService() {
        this.logger = new ConsoleLogger();
    }

    public void createUser(String username) {
        logger.log("Creating user: " + username);
    }
}
