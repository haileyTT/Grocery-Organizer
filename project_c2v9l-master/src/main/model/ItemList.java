package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents an Item list that consists of a list of items
public class ItemList implements Writable {

    private List<Item> itemList;

    // EFFECTS: create an empty list of item
    public ItemList() {
        itemList = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    // REQUIRES: item names are unique
    // MODIFIES: this
    // EFFECTS: add an item to the end of the itemList
    public void addItem(Item t) {
        itemList.add(t);
    }

    // MODIFIES: this
    // EFFECTS: remove an item with the given item name
    public void removeItem(String nm) {
        int num = -1;
        for (Item t : itemList) {
            num = num + 1;
            if (nm.equals(t.getItemName())) {
                itemList.remove(num);
                break;
            }
        }
    }

    // REQUIRES: a>0
    // EFFECTS: return the list of items with the given aisle
    public List<String> itemsWithAisle(int a) {
        List<String> listofTasksWithAisle = new ArrayList<>();
        for (Item t : itemList) {
            if (a == (t.getAisle())) {
                listofTasksWithAisle.add(t.getItemName());
            }
        }
        return listofTasksWithAisle;
    }

    // EFFECTS: return the quantity of given item
    public int quantityOfItem(String nm) {
        int num = 0;
        for (Item t : itemList) {
            if (nm.equals(t.getItemName())) {
                num = t.getQuantity();
            }
        }
        return num;
    }

    // EFFECTS: return the aisle of given item
    public int aisleOfItem(String nm) {
        int num = 0;
        for (Item t : itemList) {
            if (nm.equals(t.getItemName())) {
                num = t.getAisle();
            }
        }
        return num;
    }

    //EFFECTS: return the price of given item
    public double priceOfItem(String nm) {
        double num = 0;
        for (Item t : itemList) {
            if (nm.equals(t.getItemName())) {
                num = t.getPrice();
            }
        }
        return num;
    }


    // EFFECTS: return the number of items in the task list
    public int numOfItems() {
        return itemList.size();
    }

    // EFFECTS: return the list of item names currently in the item list
    public String showListOfItems() {
        String print = "";
        for (Item t : itemList) {
            print = print + "Item:" + t.getItemName() + " Aisle:" + t.getAisle()
                    + " Qty:" + t.getQuantity() + " Price:" + t.getPrice() + "\n\n";
        }
        return print;
    }

    // REQUIREMENTS: i>=0
    // EFFECTS: get the item with given index from the item
    public Item getItem(int i) {
        return (itemList.get(i));
    }


    // This method references code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ItemList", itemListToJson());
        return json;
    }

    // This method references code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns things in this itemlist as a JSON array
    private JSONArray itemListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}