package Persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Tests for Json
public class JsonTest {
    protected void checkItem(String itemName, Integer aisle, Integer quantity, Double price, Item item) {
        assertEquals(itemName, item.getItemName());
        assertEquals(aisle, item.getAisle());
        assertEquals(quantity, item.getQuantity());
        assertEquals(price, item.getPrice());
    }
}
