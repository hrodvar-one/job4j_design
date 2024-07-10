package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TrashTest {

    @Test
    public void whenFoodIsExpiredThenAccepted() {
        Trash trash = new Trash();
        Food food = new Food("Expired Bread", LocalDate.now().minusDays(1), LocalDate.now().minusDays(10), 100, 0);
        assertTrue(trash.accept(food));
    }

    @Test
    public void whenFoodIsNotExpiredThenRejected() {
        Trash trash = new Trash();
        Food food = new Food("Fresh Milk", LocalDate.now().plusDays(10), LocalDate.now().minusDays(1), 100, 0);
        assertFalse(trash.accept(food));
    }

    @Test
    public void whenFoodIsJustExpiredThenAccepted() {
        Trash trash = new Trash();
        Food food = new Food("Just Expired Yogurt", LocalDate.now(), LocalDate.now().minusDays(10), 100, 0);
        assertTrue(trash.accept(food));
    }

    @Test
    public void whenFoodIsFarFromExpiryThenRejected() {
        Trash trash = new Trash();
        Food food = new Food("Long Lasting Cheese", LocalDate.now().plusDays(30), LocalDate.now().minusDays(5), 100, 0);
        assertFalse(trash.accept(food));
    }

    @Test
    public void whenFoodExpiresTomorrowThenRejected() {
        Trash trash = new Trash();
        Food food = new Food("Expires Tomorrow Juice", LocalDate.now().plusDays(1), LocalDate.now().minusDays(30), 100, 0);
        assertFalse(trash.accept(food));
    }

    @Test
    public void whenFoodIsFarPastExpiryThenAccepted() {
        Trash trash = new Trash();
        Food food = new Food("Far Past Expiry", LocalDate.now().minusDays(10), LocalDate.now().minusDays(40), 100, 0);
        assertTrue(trash.accept(food));
    }
}