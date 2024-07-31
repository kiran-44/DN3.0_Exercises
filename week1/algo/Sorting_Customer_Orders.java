// Represents an order with an ID, customer name, and total cost
class Sorting_Customer_Orders {
    private String id;
    private String customer;
    private double cost;

    // Constructor to initialize an Order instance
    public Sorting_Customer_Orders(String id, String customer, double cost) {
        this.id = id;
        this.customer = customer;
        this.cost = cost;
    }

    // Getter for the order ID
    public String getId() {
        return id;
    }

    // Getter for the customer name
    public String getCustomer() {
        return customer;
    }

    // Getter for the total cost
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Customer: " + customer + ", Total Cost: $" + cost;
    }
}

// Contains sorting algorithms to sort an array of orders
class SortingAlgorithms {

    // Sorts an array of orders using bubble sort based on total cost
    public static void bubbleSortOrders(Sorting_Customer_Orders[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getCost() > orders[j + 1].getCost()) {
                    // Swap orders[j] with orders[j + 1]
                    Sorting_Customer_Orders temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Sorts an array of orders using quick sort based on total cost
    public static void quickSortOrders(Sorting_Customer_Orders[] orders, int low, int high) {
        if (low < high) {
            int partitionIndex = partitionOrders(orders, low, high);
            quickSortOrders(orders, low, partitionIndex - 1);
            quickSortOrders(orders, partitionIndex + 1, high);
        }
    }

    // Partitions the array for quick sort
    private static int partitionOrders(Sorting_Customer_Orders[] orders, int low, int high) {
        double pivotValue = orders[high].getCost();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getCost() <= pivotValue) {
                i++;
                // Swap orders[i] with orders[j]
                Sorting_Customer_Orders temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        // Swap the pivot element with the element at i + 1
        Sorting_Customer_Orders temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
}

// Demonstrates sorting algorithms on an array of orders
class Main {
    public static void main(String[] args) {
        Sorting_Customer_Orders[] ordersArray = {
            new Sorting_Customer_Orders("O001", "Alice", 250.50),
            new Sorting_Customer_Orders("O002", "Bob", 150.75),
            new Sorting_Customer_Orders("O003", "Charlie", 300.10),
            new Sorting_Customer_Orders("O004", "David", 175.20),
            new Sorting_Customer_Orders("O005", "Eve", 210.80)
        };

        System.out.println("Before Bubble Sort:");
        displayOrders(ordersArray);

        // Perform bubble sort and display the sorted orders
        SortingAlgorithms.bubbleSortOrders(ordersArray);
        System.out.println("\nAfter Bubble Sort:");
        displayOrders(ordersArray);

        // Reset orders array
        ordersArray = new Sorting_Customer_Orders[]{
            new Sorting_Customer_Orders("O001", "Alice", 250.50),
            new Sorting_Customer_Orders("O002", "Bob", 150.75),
            new Sorting_Customer_Orders("O003", "Charlie", 300.10),
            new Sorting_Customer_Orders("O004", "David", 175.20),
            new Sorting_Customer_Orders("O005", "Eve", 210.80)
        };

        System.out.println("\nBefore Quick Sort:");
        displayOrders(ordersArray);

        // Perform quick sort and display the sorted orders
        SortingAlgorithms.quickSortOrders(ordersArray, 0, ordersArray.length - 1);
        System.out.println("\nAfter Quick Sort:");
        displayOrders(ordersArray);
    }

    // Displays the details of all orders in the array
    public static void displayOrders(Sorting_Customer_Orders[] orders) {
        for (Sorting_Customer_Orders order : orders) {
            System.out.println(order);
        }
    }
}
