package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


// GUI of item application
public class GUI extends JPanel
        implements ListSelectionListener {

    // fields
    private ImageIcon image1;
    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton searchButton;
    private JButton removeButton;
    private JButton checkButton;
    private JTextField itemName;
    private JTextField aisle;
    private JTextField quantity;
    private JTextField price;
    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private DefaultListModel listModel;
    private JList list;
    ItemList il1 = new ItemList();

    // This method references code from ListDemo
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https:docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    // EFFECTS: runs the new GUI
    public GUI() throws FileNotFoundException {

        // load the image icon
        loadImage();

        //Create the list and put it in a scroll pane.
        JScrollPane listScrollPane = makeScrollPanel();

        // making the button
        makeButtons();
        // making text fields
        makeTextfields();

        // make the button work
        addButton.addActionListener(new AddListener());
        saveButton.addActionListener(new SaveListener());
        loadButton.addActionListener(new LoadListener());
        searchButton.addActionListener(new SearchListener());
        removeButton.addActionListener(new RemoveListener());
        checkButton.addActionListener(new CheckListener());


        // Create and set up the window.
        frame = new JFrame("Grocery Organizer");

        // print out the event log when the window is closed
        closeListener();


        // create and set up the content pane
        makePanels();

        frame.add(panel, BorderLayout.PAGE_END);
        frame.add(listScrollPane, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);
        frame.setTitle("Grocery Organizer");

        //Display the window
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    // this method references code from https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
    // MODIFIES: this
    // EFFECTS: close the window and print out the event log
    private void closeListener() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next);
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: make a scroll panel
    private JScrollPane makeScrollPanel() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    // This method references C3-LectureLabStarter https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabSolution.git
    // MODIFIES: this
    // EFFECTS: load an image
    private void loadImage() {
        String sep = System.getProperty("file.separator");
        image1 = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "groceryPic1.jpg");
    }

    // MODIFIES: this
    // EFFECTS: make other panels
    private void makePanels() {
        panel = new JPanel();
        panel2 = new JPanel();

        panel.setLayout(new GridLayout(2, 4));
        panel2.setLayout(new GridLayout(0, 1));

        panel2.add(addButton);
        panel2.add(removeButton);
        panel2.add(saveButton);
        panel2.add(loadButton);
        panel2.add(searchButton);
        panel2.add(checkButton);
        panel.add(new JLabel("Item"));
        panel.add(new JLabel("Aisle"));
        panel.add(new JLabel("Qty"));
        panel.add(new JLabel("Price"));
        panel.add(itemName);
        panel.add(aisle);
        panel.add(quantity);
        panel.add(price);
    }

    // MODIFIES: this
    // EFFECTS: make text fields
    private void makeTextfields() {
        itemName = new JTextField();
        aisle = new JTextField();
        quantity = new JTextField();
        price = new JTextField();
    }

    // MODIFIES: this
    // EFFECTS: make Buttons
    private void makeButtons() {
        addButton = new JButton("Add Item");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        searchButton = new JButton("Search Item");
        removeButton = new JButton("Remove Item");
        checkButton = new JButton("Check Item");
    }

    @Override
    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
    }


    // This class references code from
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    // ActionListener of the addButton
    class AddListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: add an item to the list
        @Override
        public void actionPerformed(ActionEvent e) {
            Item i = new Item(itemName.getText(), Integer.parseInt(aisle.getText()),
                    Integer.parseInt(quantity.getText()), Double.parseDouble(price.getText()));
            il1.addItem(i);
            String item = "Item:" + i.getItemName() + " Aisle:" + i.getAisle()
                    + " Qty:" + i.getQuantity() + " Price:" + i.getPrice();
            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }
            listModel.insertElementAt(item, index);
            itemName.setText("");
            aisle.setText("");
            quantity.setText("");
            price.setText("");
            Event event = new Event("Added " + i.getItemName() + " to the list");
            EventLog.getInstance().logEvent(event);
        }
    }

    // ActionListener of the saveButton
    class SaveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: save the list of items
        @Override
        public void actionPerformed(ActionEvent e) {
            String jsonStore = "./data/workroom.json";
            JsonWriter jsonWriter;
            jsonWriter = new JsonWriter(jsonStore);

            try {
                jsonWriter.open();
                jsonWriter.write(il1);
                jsonWriter.close();
                System.out.println("Saved " + "items" + " to " + jsonStore);
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to write to file: " + jsonStore);
            }
        }
    }

    // ActionListener of the loadButton
    class LoadListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: load the list of items
        @Override
        public void actionPerformed(ActionEvent e) {
            String jsonStore = "./data/workroom.json";
            JsonReader jsonReader;
            jsonReader = new JsonReader(jsonStore);

            try {
                il1 = jsonReader.read();
                for (Item i : il1.getItemList()) {
                    String str = "Item:" + i.getItemName() + " Aisle:"
                            + i.getAisle() + " Qty:" + i.getQuantity() + " Price:" + i.getPrice();
                    listModel.addElement(str);
                }
            } catch (IOException ex) {
                System.out.println("Unable to read from file: " + jsonStore);
            }
        }
    }

    // ActionListener of the searchButton
    class SearchListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: make a popup window that shows the items in the given aisle
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer a = Integer.parseInt(aisle.getText());
            String itemsWithAisle = il1.itemsWithAisle(a).toString();
            System.out.println(itemsWithAisle);
            JOptionPane.showMessageDialog(frame, "Items in aisle " + a + ": " + itemsWithAisle,
                    "Grocery",
                    JOptionPane.INFORMATION_MESSAGE,
                    image1);
        }
    }

    // This class references https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    // ActionListener of the RemoveButton
    class RemoveListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: remove an item
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            Object[] possibilities = null;
            String s = (String)JOptionPane.showInputDialog(
                    frame,
                    "Enter item name to confirm removal",
                    "Grocery",
                    JOptionPane.PLAIN_MESSAGE,
                    image1,
                    possibilities, "");
            il1.removeItem(s);
            listModel.remove(index);
            Event event = new Event("Removed " + s + " from the list");
            EventLog.getInstance().logEvent(event);
        }
    }

    // ActionListener of the checkButton
    class CheckListener implements ActionListener {
        // MODIFIES: this
        // EFFECTS: make a popup window that shows information of the item with the given name
        @Override
        public void actionPerformed(ActionEvent e) {
            String nm = itemName.getText();
            il1.aisleOfItem(nm);
            il1.quantityOfItem(nm);
            il1.priceOfItem(nm);
            JOptionPane.showMessageDialog(frame,
                    "Aisle: " + il1.aisleOfItem(nm) + "\nQty: " + il1.quantityOfItem(nm)
                            + "\nPrice: " + il1.priceOfItem(nm),
                    "Grocery",
                    JOptionPane.INFORMATION_MESSAGE,
                    image1);
        }
    }

}
