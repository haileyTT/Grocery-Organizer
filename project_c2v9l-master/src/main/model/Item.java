package model;

import org.json.JSONObject;
import persistence.Writable;

// This class references code from class Thingy in project JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents an item having a name, aisle, quantity and price (in dollars)
public class Item {

    private String itemName;
    private int aisle;
    private int quantity;
    private double price;

    // REQUIREMENTS: aisle>0, quantity>0, price>0
    // EFFECTS: create a new item
    public Item(String itemName, int aisle, int quantity, double price) {
        this.itemName = itemName;
        this.aisle = aisle;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAisle() {
        return aisle;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // This method references code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", itemName);
        json.put("aisle", aisle);
        json.put("quantity", quantity);
        json.put("price", price);
        return json;
    }
}

