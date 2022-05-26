package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Item class and ItemList class
class ItemTest {
    ItemList tl;
    Item t1;
    Item t2;
    Item t3;

    @BeforeEach
    public void runBefore() {
        tl = new ItemList();
        t1 = new Item("eggs", 1, 2, 1.5);
        t2 = new Item("paper", 2, 3, 1);
        t3 = new Item("meat", 2, 3, 1);
    }

    @Test
    public void testremoveTask() {
        tl.addItem(t1);
        tl.addItem(t2);
        tl.addItem(t3);
        tl.removeItem("eggs");
        assertEquals(2, tl.numOfItems());
        assertEquals("paper", (tl.getItem(0)).getItemName());
        assertEquals("meat", (tl.getItem(1)).getItemName());
        tl.removeItem("paper");
        assertEquals("meat", (tl.getItem(0)).getItemName());
    }

    @Test
    public void testremoveNotEqualName() {
        tl.addItem(t1);
        tl.removeItem("hs");
        assertEquals(1, tl.numOfItems());
    }

    @Test
    public void testtasksWithAisle() {
        tl.addItem(t1);
        tl.addItem(t2);
        tl.addItem(t3);
        assertEquals(2, (tl.itemsWithAisle(2)).size());
        assertEquals(((tl.itemsWithAisle(2)).get(0)), "paper");
    }

    @Test
    public void testnumOfTasks() {
        tl.addItem(t1);
        tl.addItem(t2);
        assertEquals(2, tl.numOfItems());
    }

    @Test
    public void testgetItemName() {
        assertEquals(t1.getItemName(), "eggs");
    }

    @Test
    public void testgetAisle() {
        assertEquals(t1.getAisle(), 1);
    }

    @Test
    public void testgetPrice() {
        assertEquals(t1.getPrice(), 1.5);
    }

    @Test
    public void testgetItem() {
        tl.addItem(t1);
        assertEquals(tl.getItem(0).getItemName(), "eggs");
    }

    @Test
    public void testshowListOfTasks() {
        tl.addItem(t1);
        tl.showListOfItems();
        assertTrue(tl.showListOfItems().equals("Item:eggs Aisle:1 Qty:2 Price:1.5\n\n"));
    }


    @Test
    public void testquantity0fItem() {
        tl.addItem(t1);
        assertEquals(2, tl.quantityOfItem("eggs"));
    }

    @Test
    public void testquantity0fItemNotEqual() {
        tl.addItem(t1);
        assertEquals(0, tl.quantityOfItem("bacon"));
    }

    @Test
    public void testaisle0fItem() {
        tl.addItem(t1);
        assertEquals(1, tl.aisleOfItem("eggs"));
    }

    @Test
    public void testaisle0fItemNotEqual() {
        tl.addItem(t1);
        assertEquals(0, tl.aisleOfItem("bacon"));
    }

    @Test
    public void testprice0fItem() {
        tl.addItem(t1);
        assertEquals(1.5, tl.priceOfItem("eggs"));
    }

    @Test
    public void testprice0fItemNotEqual() {
        tl.addItem(t1);
        assertEquals(0, tl.priceOfItem("bacon"));
    }


}