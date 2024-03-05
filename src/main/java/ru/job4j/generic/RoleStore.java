package ru.job4j.generic;

public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role role) {
        store.add(role);
    }

    @Override
    public boolean replace(String id, Role role) {
        return store.replace(id, role);
    }

    @Override
    public void delete(String id) {
        store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}
