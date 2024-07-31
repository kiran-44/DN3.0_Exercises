import java.util.Arrays;
import java.util.Comparator;

// Class representing a product with an ID, name, and type
class Product {
    private String id;
    private String name;
    private String type;

    // Constructor to initialize a Product instance
    public Product(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getter for the product ID
    public String getId() {
        return id;
    }

    // Getter for the product name
    public String getName() {
        return name;
    }

    // Getter for the product type
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Type: " + type;
    }
}

// Class containing search algorithms for products
class SearchAlgorithms {
    // Performs a linear search to find a product by its name
    public static Product findByNameLinear(Product[] productArray, String nameToFind) {
        for (Product product : productArray) {
            if (product.getName().equalsIgnoreCase(nameToFind)) {
                return product;
            }
        }
        return null; // Product not found
    }

    // Performs a binary search to find a product by its name
    static Product findByNameBinary(Product[] productArray, String nameToFind) {
        // Sort products based on their names to enable binary search
        Arrays.sort(productArray, Comparator.comparing(Product::getName));
        
        int start = 0;
        int end = productArray.length - 1; 
        
        while (start <= end) {
            int middle = start + (end - start) / 2;
            int comparisonResult = productArray[middle].getName().compareToIgnoreCase(nameToFind); 
            
            if (comparisonResult == 0) {
                return productArray[middle]; // Product found
            } else if (comparisonResult < 0) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return null; // Product not found
    }
}

// Main class to demonstrate the usage of Product and SearchAlgorithms classes
class Main {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Tablet", "Electronics"),
            new Product("P004", "Monitor", "Electronics"),
            new Product("P005", "Keyboard", "Accessories")
        };

        // Demonstrating linear search to find a product by name
        System.out.println("Linear Search:");
        String nameToSearch = "Tablet";
        Product resultProduct = SearchAlgorithms.findByNameLinear(products, nameToSearch);
        if (resultProduct != null) {
            System.out.println("Found: " + resultProduct);
        } else {
            System.out.println("Product not found.");
        }

        // Demonstrating binary search to find a product by name
        System.out.println("\nBinary Search:");
        nameToSearch = "Monitor";
        resultProduct = SearchAlgorithms.findByNameBinary(products, nameToSearch);
        if (resultProduct != null) {
            System.out.println("Found: " + resultProduct);
        } else {
            System.out.println("Product not found.");
        }
    }
}
