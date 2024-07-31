import java.util.HashMap;

class Item {
    private String itemId;
    private String itemName;
    private int stockQuantity;
    private double unitPrice;

    // Constructor to initialize an Item instance
    public Item(String itemId, String itemName, int stockQuantity, double unitPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
    }

    // Getter for the item ID
    public String getItemId() {
        return itemId;
    }

    // Setter for the item ID
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    // Getter for the item name
    public String getItemName() {
        return itemName;
    }

    // Setter for the item name
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Getter for the stock quantity
    public int getStockQuantity() {
        return stockQuantity;
    }

    // Setter for the stock quantity
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // Getter for the unit price
    public double getUnitPrice() {
        return unitPrice;
    }

    // Setter for the unit price
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + "\n" +
               "Item Name: " + itemName + "\n" +
               "Stock Quantity: " + stockQuantity + "\n" +
               "Unit Price: " + unitPrice + "\n";
    }
}



class StockController {
    private HashMap<String, Item> itemCatalog;

    // Constructor to initialize the StockController with an empty item catalog
    public StockController() {
        itemCatalog = new HashMap<>();
    }

    // Adds an item to the catalog
    public void addItem(Item item) {
        if (item != null) {
            itemCatalog.put(item.getItemId(), item);
            System.out.println("Item added: " + item.getItemName());
        } else {
            System.out.println("Cannot add null item.");
        }
    }

    // Updates an item in the catalog
    public void updateItem(Item item) {
        if (item != null && itemCatalog.containsKey(item.getItemId())) {
            itemCatalog.put(item.getItemId(), item);
            System.out.println("Item updated: " + item.getItemName());
        } else if (item == null) {
            System.out.println("Cannot update null item.");
        } else {
            System.out.println("Item with ID " + item.getItemId() + " not found.");
        }
    }

    // Removes an item from the catalog
    public void removeItem(String itemId) {
        if (itemId != null && itemCatalog.containsKey(itemId)) {
            itemCatalog.remove(itemId);
            System.out.println("Item removed with ID: " + itemId);
        } else if (itemId == null) {
            System.out.println("Item ID cannot be null.");
        } else {
            System.out.println("Item with ID " + itemId + " not found.");
        }
    }

    // Displays all items in the catalog
    public void showAllItems() {
        if (itemCatalog.isEmpty()) {
            System.out.println("No items in the catalog.");
        } else {
            for (Item item : itemCatalog.values()) {
                System.out.println(item);
            }
        }
    }

    public static void main(String[] args) {
        StockController controller = new StockController();

        // Adding items to the catalog
        Item item1 = new Item("I001", "Item_One", 50, 999.99);
        Item item2 = new Item("I002", "Item_Two", 30, 899.99);
        Item item3 = new Item("I003", "Item_Three", 100, 349.99);
        Item item4 = new Item("I004", "Item_Four", 25, 1249.99);
        Item item5 = new Item("I005", "Item_Five", 15, 2399.99);

        controller.addItem(item1);
        controller.addItem(item2);
        controller.addItem(item3);
        controller.addItem(item4);
        controller.addItem(item5);

        // Displaying all items
        controller.showAllItems();

        // Updating an item
        Item updatedItem1 = new Item("I001", "Item_One", 60, 949.99);
        controller.updateItem(updatedItem1);

        // Displaying all items after update
        controller.showAllItems();

        // Removing an item
        controller.removeItem("I002");

        // Displaying all items after removal
        controller.showAllItems();
    }
}
