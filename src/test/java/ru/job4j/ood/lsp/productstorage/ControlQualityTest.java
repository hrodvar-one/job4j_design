package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}