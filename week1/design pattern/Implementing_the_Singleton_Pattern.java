// Singleton class for logging utility
class LogManager {
    // Single instance of LogManager
    private static LogManager uniqueInstance;
    
    // Private constructor to prevent instantiation from outside
    private LogManager() {
        // Initialization code for the logger
    }

    // Method to provide access to the single instance of LogManager
    public static LogManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LogManager();
        }
        return uniqueInstance;
    }

    // Method to log messages
    public void record(String message) {
        System.out.println("Log Entry: " + message);
    }
}

// Test class to verify Singleton pattern implementation
class SingletonDemo {
    public static void main(String[] args) {
        // Fetching the single instance of LogManager
        LogManager logger1 = LogManager.getInstance();
        LogManager logger2 = LogManager.getInstance();
        
        // Logging messages
        logger1.record("This is the first log entry.");
        logger2.record("This is the second log entry.");

        // Verifying that both logger1 and logger2 refer to the same instance
        if (logger1 == logger2) {
            System.out.println("logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}
