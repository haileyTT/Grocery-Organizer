package Persistence;

import model.Item;
import model.ItemList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Tests for JsonWriter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ItemList itemList = new ItemList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyItemList() {
        try {
            ItemList itemList = new ItemList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyItemList.json");
            writer.open();
            writer.write(itemList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyItemList.json");
            itemList = reader.read();
            assertEquals(0, itemList.numOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralItemList() {
        try {
            ItemList itemList = new ItemList();
            itemList.addItem(new Item("egg", 1, 2, 3.0));
            itemList.addItem(new Item("bacon", 2, 3, 4.0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralItemList.json");
            writer.open();
            writer.write(itemList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralItemList.json");
            itemList = reader.read();
            List<Item> items = itemList.getItemList();
            assertEquals(2, items.size());
            checkItem("egg", 1, 2, 3.0, items.get(0));
            checkItem("bacon", 2, 3, 4.0, items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
