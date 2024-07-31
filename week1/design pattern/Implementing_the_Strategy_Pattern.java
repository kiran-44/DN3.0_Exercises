// Define the PaymentMethod interface
interface PaymentMethod {
    void processPayment(double amount);
}

// Implement concrete payment methods
class Implementing_the_Strategy_Pattern implements PaymentMethod {
    private String cardHolderName;
    private String cardNumber;
    private String securityCode;
    private String expirationDate;

    public Implementing_the_Strategy_Pattern(String cardHolderName, String cardNumber, String securityCode, String expirationDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processed payment of $" + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentMethod {
    private String accountEmail;
    private String accountPassword;

    public PayPalPayment(String accountEmail, String accountPassword) {
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processed payment of $" + amount + " using PayPal.");
    }
}

// Implement the PaymentProcessor context
class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void executePayment(double amount) {
        paymentMethod.processPayment(amount);
    }
}

// Test the Strategy Pattern
class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Test payment using Credit Card
        processor.setPaymentMethod(new Implementing_the_Strategy_Pattern("John Doe", "1234567890123456", "123", "12/23"));
        processor.executePayment(100.0);

        // Test payment using PayPal
        processor.setPaymentMethod(new PayPalPayment("john.doe@example.com", "password123"));
        processor.executePayment(200.0);
    }
}
