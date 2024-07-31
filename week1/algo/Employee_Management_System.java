// Represents an employee with an ID, name, position, and salary
class Employee {
    private int id;
    private String fullName;
    private String jobTitle;
    private double annualSalary;

    // Constructor to initialize an Employee instance
    public Employee(int id, String fullName, String jobTitle, double annualSalary) {
        this.id = id;
        this.fullName = fullName;
        this.jobTitle = jobTitle;
        this.annualSalary = annualSalary;
    }

    // Getter for employee ID
    public int getId() {
        return id;
    }

    // Getter for employee name
    public String getFullName() {
        return fullName;
    }

    // Getter for employee position
    public String getJobTitle() {
        return jobTitle;
    }

    // Getter for employee salary
    public double getAnnualSalary() {
        return annualSalary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + fullName + ", Position: " + jobTitle + ", Salary: $" + annualSalary;
    }
}

// Manages a list of employees using an array
class EmployeeManager {
    private Employee[] employeeList;
    private int currentSize;

    // Constructor to initialize EmployeeManager with a specified capacity
    public EmployeeManager(int capacity) {
        employeeList = new Employee[capacity];
        currentSize = 0;
    }

    // Adds a new employee to the array
    public void addEmployee(Employee employee) {
        if (currentSize < employeeList.length) {
            employeeList[currentSize++] = employee;
        } else {
            System.out.println("The employee list is full. Unable to add more employees.");
        }
    }

    // Searches for an employee using their ID
    public Employee findEmployeeById(int id) {
        for (int i = 0; i < currentSize; i++) {
            if (employeeList[i].getId() == id) {
                return employeeList[i];
            }
        }
        return null;
    }

    // Prints the details of all employees in the list
    public void displayAllEmployees() {
        for (int i = 0; i < currentSize; i++) {
            System.out.println(employeeList[i]);
        }
    }

    // Removes an employee from the list using their ID
    public boolean removeEmployeeById(int id) {
        for (int i = 0; i < currentSize; i++) {
            if (employeeList[i].getId() == id) {
                // Shift employees to the left to fill the gap
                for (int j = i; j < currentSize - 1; j++) {
                    employeeList[j] = employeeList[j + 1];
                }
                employeeList[--currentSize] = null;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(10);
        // Adding sample employees to the list
        manager.addEmployee(new Employee(1, "Alice Johnson", "Software Developer", 70000));
        manager.addEmployee(new Employee(2, "Bob Smith", "Project Manager", 85000));
        manager.addEmployee(new Employee(3, "Charlie Brown", "Business Analyst", 60000));
        manager.addEmployee(new Employee(4, "David Wilson", "UI/UX Designer", 65000));
        
        System.out.println("Employee List:");
        manager.displayAllEmployees(); // Display all employees

        System.out.println("\nSearching for employee with ID 3:");
        Employee employee = manager.findEmployeeById(3); // Find an employee by ID
        if (employee != null) {
            System.out.println("Found: " + employee);
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nRemoving employee with ID 2:");
        boolean wasRemoved = manager.removeEmployeeById(2); // Remove an employee by ID
        System.out.println("Removal successful: " + wasRemoved);

        // Display all employees after removal
        System.out.println("\nEmployee List after removal:");
        manager.displayAllEmployees();
    }
}
