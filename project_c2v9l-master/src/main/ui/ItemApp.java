package ui;

import model.Item;
import model.ItemList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// This class references code from class TellerApp in project AccountNotRobust
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp

// Item application
public class ItemApp {

    private ItemList itemList;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the application
    public ItemApp() throws FileNotFoundException {
        itemList = new ItemList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTask();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTask() {
        System.out.println("\nWelcome to grocery organizer");
        boolean keepgoing = true;

        while (keepgoing) {
            displayMenu();
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt();

            if (selection == 8) {
                keepgoing = false;
                System.out.println("Goodbye!");
            } else {
                processSelection(selection);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user selection
    private void processSelection(Integer selection) {
        if (selection == 1) {
            doAddItem();
        } else if (selection == 2) {
            doRemoveItem();
        } else if (selection == 3) {
            doitemswithaisle();
        } else if (selection == 4) {
            doShowItemInfo();
        } else if (selection == 5) {
            doShowItem();
        } else if (selection == 6) {
            saveItemList();
        } else if (selection == 7) {
            loadItemList();
        } else {
            System.out.println("Invalid entry try again");
        }
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add an item");
        System.out.println("\t2 -> Remove an item");
        System.out.println("\t3 -> show items with given aisle");
        System.out.println("\t4 -> show quantity, aisle, and price of given item");
        System.out.println("\t5 -> show current list of items");
        System.out.println("\t6 -> save item list to file");
        System.out.println("\t7 -> load item list from file");
        System.out.println("\t8 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add an item to the item list
    private void doAddItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item");
        String tm = sc.nextLine();
        System.out.println("Enter the aisle");
        Integer a = sc.nextInt();
        System.out.println("Enter the amount of item");
        Integer n = sc.nextInt();
        System.out.println("Enter the price of item");
        double p = sc.nextDouble();
        Item t = new Item(tm, a, n, p);
        itemList.addItem(t);
        System.out.println("Item added successfully!");
    }

    // MODIFIES: this
    // EFFECTS: remove an item from the list
    private void doRemoveItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item");
        String nm = sc.nextLine();
        itemList.removeItem(nm);
        System.out.println("Press 5 to see if successfully removed");
    }

    // EFFECTS: show a list of items with given aisle
    private void doitemswithaisle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the aisle");
        Integer a = sc.nextInt();
        System.out.println(itemList.itemsWithAisle(a));
    }

    // EFFECTS: show all information of given item
    private void doShowItemInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the item");
        String nm = sc.nextLine();
        System.out.println("\nquantity: " + itemList.quantityOfItem(nm) + "\naisle: " + itemList.aisleOfItem(nm)
                + "\nprice: " + itemList.priceOfItem(nm));
    }


    // EFFECTS: show the list of items
    private void doShowItem() {
        System.out.println(itemList.showListOfItems());
    }

    // This method references code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the itemlist to file
    private void saveItemList() {
        try {
            jsonWriter.open();
            jsonWriter.write(itemList);
            jsonWriter.close();
            System.out.println("Saved " + "items" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // This method references code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads itemlist from file
    private void loadItemList() {
        try {
            itemList = jsonReader.read();
            System.out.println("Loaded " + "items" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}


