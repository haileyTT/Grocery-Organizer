package Persistence;

import model.Item;
import model.ItemList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Tests for JsonReader
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ItemList itemList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyItemList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyItemList.json");
        try {
            ItemList itemList = reader.read();
            assertEquals(0, itemList.numOfItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralItemList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralItemList.json");
        try {
            ItemList itemList = reader.read();
            List<Item> il = itemList.getItemList();
            assertEquals(2, il.size());
            checkItem("egg", 1, 2, 3.0, il.get(0));
            checkItem("bacon", 2, 3, 4.0, il.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
