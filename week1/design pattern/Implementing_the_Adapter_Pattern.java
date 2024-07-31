interface PaymentGateway {
    void handlePayment(double amount);
}

class PayPalService {
    public void executePayment(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through PayPal.");
    }
}

class StripeService {
    public void processCharge(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through Stripe.");
    }
}

class AmazonPayService {
    public void performTransaction(double amount) {
        System.out.println("Processing payment of Rs." + amount + " through Amazon Pay.");
    }
}

class PayPalAdapter implements PaymentGateway {
    private PayPalService payPalService;

    public PayPalAdapter(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @Override
    public void handlePayment(double amount) {
        payPalService.executePayment(amount);
    }
}

class StripeAdapter implements PaymentGateway {
    private StripeService stripeService;

    public StripeAdapter(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public void handlePayment(double amount) {
        stripeService.processCharge(amount);
    }
}

class AmazonPayAdapter implements PaymentGateway {
    private AmazonPayService amazonPayService;

    public AmazonPayAdapter(AmazonPayService amazonPayService) {
        this.amazonPayService = amazonPayService;
    }

    @Override
    public void handlePayment(double amount) {
        amazonPayService.performTransaction(amount);
    }
}

class AdapterPatternDemo {
    public static void main(String[] args) {
        PayPalService payPalService = new PayPalService();
        StripeService stripeService = new StripeService();
        AmazonPayService amazonPayService = new AmazonPayService();

        PaymentGateway payPalAdapter = new PayPalAdapter(payPalService);
        PaymentGateway stripeAdapter = new StripeAdapter(stripeService);
        PaymentGateway amazonPayAdapter = new AmazonPayAdapter(amazonPayService);

        payPalAdapter.handlePayment(100.00);
        stripeAdapter.handlePayment(200.00);
        amazonPayAdapter.handlePayment(300.00);
    }
}
