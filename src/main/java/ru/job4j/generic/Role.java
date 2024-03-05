package ru.job4j.generic;

public class Role extends Base {
    private final String roleName;

    public Role(String id, String name) {
        super(id);
        this.roleName = name;
    }

    public String getRoleName() {
        return roleName;
    }
}
