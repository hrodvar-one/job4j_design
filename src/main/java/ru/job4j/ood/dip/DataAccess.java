package ru.job4j.ood.dip;

public class DataAccess {
    private MySQLDatabase database;

    public DataAccess() {
        this.database = new MySQLDatabase();
    }

    public void fetchData() {
        database.connect();
    }
}
