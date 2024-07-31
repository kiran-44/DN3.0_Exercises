// Represents a Task with an ID, name, and status
class Task {
    private int taskId;
    private String taskName;
    private String status;

    // Constructor to initialize a Task instance
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    // Getter for task ID
    public int getTaskId() {
        return taskId;
    }

    // Getter for task name
    public String getTaskName() {
        return taskName;
    }

    // Getter for task status
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Status: " + status;
    }
}

// Represents a node in the linked list, containing a Task and a reference to the next node
class Node {
    Task task;
    Node next;

    // Constructor to initialize a Node with a Task
    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

// Manages a list of tasks using a linked list
class TaskLinkedList {
    private Node head;

    // Constructor to initialize an empty TaskLinkedList
    public TaskLinkedList() {
        this.head = null;
    }

    // Adds a task to the end of the linked list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Searches for a task by its ID
    public Task searchTaskById(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Traverses the linked list and prints all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Deletes a task by its ID
    public boolean deleteTaskById(int taskId) {
        if (head == null) return false;
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.task.getTaskId() != taskId) {
            current = current.next;
        }
        if (current.next == null) return false;
        current.next = current.next.next;
        return true;
    }
}

// Demonstrates the usage of the TaskLinkedList class
class Main {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        // Adding new tasks to the list
        taskList.addTask(new Task(1, "Design UI", "In Progress"));
        taskList.addTask(new Task(2, "Develop Backend", "Not Started"));
        taskList.addTask(new Task(3, "Write Tests", "Not Started"));
        taskList.addTask(new Task(4, "Deploy Application", "Completed"));

        // Display all tasks
        System.out.println("All tasks:");
        taskList.traverseTasks();

        // Search for a specific task by its ID
        System.out.println("\nSearching for task with ID 3:");
        Task task = taskList.searchTaskById(3);
        if (task != null) {
            System.out.println("Found: " + task);
        } else {
            System.out.println("Task not found.");
        }

        // Delete a task by its ID
        System.out.println("\nDeleting task with ID 2:");
        boolean isDeleted = taskList.deleteTaskById(2);
        System.out.println("Deleted: " + isDeleted);

        // Display all tasks after deletion
        System.out.println("\nAll tasks after deletion:");
        taskList.traverseTasks();
    }
}
