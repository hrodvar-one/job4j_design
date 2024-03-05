package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Administrator");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Administrator");
    }

    @Test
    void whenReplaceThenRoleNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Programmer");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("10", new Role("10", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Administrator");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Administrator");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        boolean result = store.replace("1", new Role("1", "Programmer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        boolean result = store.replace("10", new Role("10", "Programmer"));
        assertThat(result).isFalse();
    }
}