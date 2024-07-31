interface CustomerDataRepository {
    String retrieveCustomerById(String customerId);
}

// Implement Concrete Repository
class CustomerDataRepositoryImpl implements CustomerDataRepository {
    @Override
    public String retrieveCustomerById(String customerId) {
        // This is a mock implementation. In a real application, it would interact with a database.
        if (customerId.equals("1")) {
            return "John Doe";
        } else {
            return "Customer not found";
        }
    }
}

// Define Service Class
class CustomerManagementService {
    private CustomerDataRepository dataRepository;

    // Implement Dependency Injection
    public CustomerManagementService(CustomerDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public String fetchCustomerInfo(String customerId) {
        return dataRepository.retrieveCustomerById(customerId);
    }
}

// Test the Dependency Injection Implementation
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // Create a CustomerDataRepository instance
        CustomerDataRepository dataRepository = new CustomerDataRepositoryImpl();

        // Inject the repository into the service
        CustomerManagementService service = new CustomerManagementService(dataRepository);

        // Use the service to get customer information
        String customerInfo = service.fetchCustomerInfo("1");
        System.out.println("Customer Info: " + customerInfo);
    }
}
