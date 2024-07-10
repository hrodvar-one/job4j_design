package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WarehouseTest {

    @Test
    public void whenFoodIsFreshThenAccepted() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food("Apple", LocalDate.now().plusDays(30), LocalDate.now()
                .minusDays(5), 100, 0);
        assertTrue(warehouse.accept(food));
    }

    @Test
    public void whenFoodIsStaleThenRejected() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food("Banana", LocalDate.now().plusDays(10), LocalDate.now()
                .minusDays(20), 100, 0);
        assertFalse(warehouse.accept(food));
    }

    @Test
    public void whenFoodIsJustCreatedThenAccepted() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food("Carrot", LocalDate.now()
                .plusDays(60), LocalDate.now(), 100, 0);
        assertTrue(warehouse.accept(food));
    }

    @Test
    public void whenFoodIsQuarterLifeThenAccepted() {
        Warehouse warehouse = new Warehouse();
        Food food = new Food("Orange", LocalDate.now().plusDays(20), LocalDate.now()
                .minusDays(5), 100, 0);
        assertTrue(warehouse.accept(food));
    }
}