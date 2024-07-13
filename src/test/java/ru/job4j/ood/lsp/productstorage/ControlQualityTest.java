package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenRedistributeThenFoodInCorrectStore() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Arrays.asList(warehouse, shop, trash));

        Food food = new Food("Milk", LocalDate.now().plusDays(30), LocalDate.now()
                .minusDays(10), 50, 0);
        controlQuality.redistribute(food);

        assertTrue(shop.getAll().contains(food));
    }

    @Test
    public void whenResortThenRedistributeAll() {
        Food food1 = new Food("Milk", LocalDate.now().plusDays(10), LocalDate.now(), 100, 0);
        Food food2 = new Food("Bread", LocalDate.now().minusDays(1), LocalDate.now().minusDays(10), 50, 0);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();

        warehouse.add(food1);
        shop.add(food2);

        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);

        ControlQuality control = new ControlQuality(stores);

        assertEquals(1, warehouse.getAll().size());
        assertEquals(1, shop.getAll().size());
        assertEquals(0, trash.getAll().size());

        control.resort();

        assertTrue(warehouse.getAll().contains(food1));
        assertTrue(trash.getAll().contains(food2));
        assertTrue(shop.getAll().isEmpty());
    }
}