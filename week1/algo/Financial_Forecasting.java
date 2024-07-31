// This class performs financial forecasting using recursive calculations
class FinancialForecasting {

    // Recursive method to calculate the future value of an investment
    public static double computeFutureValue(double initialAmount, double annualGrowthRate, int numPeriods) {
        // Base case: if the number of periods is zero, return the initial amount
        if (numPeriods == 0) {
            return initialAmount;
        }
        // Recursive case: calculate the value after one period and call the method with reduced periods
        return computeFutureValue(initialAmount * (1 + annualGrowthRate), annualGrowthRate, numPeriods - 1);
    }

    // Main method to test the future value prediction
    public static void main(String[] args) {
        double initialAmount = 1000.0;  // Initial amount of money
        double annualGrowthRate = 0.05; // Annual growth rate of 5%
        int numPeriods = 10;            // Number of periods (years) for growth
        
        // Calculate the future value after the given number of periods
        double futureValue = computeFutureValue(initialAmount, annualGrowthRate, numPeriods);
        
        // Output the predicted future value
        System.out.println("Projected Future Value: $" + futureValue);
    }
}
