import java.util.ArrayList;
import java.util.List;

interface Market {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class StockExchange implements Market {
    private List<Observer> observerList;
    private double currentStockPrice;

    public StockExchange() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update(currentStockPrice);
        }
    }

    public void setStockPrice(double price) {
        this.currentStockPrice = price;
        notifyObservers();
    }
}

interface Observer {
    void update(double price);
}

class MobileNotification implements Observer {
    private String applicationName;

    public MobileNotification(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void update(double price) {
        System.out.println(applicationName + " received stock price update: " + price);
    }
}

class WebNotification implements Observer {
    private String applicationName;

    public WebNotification(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void update(double price) {
        System.out.println(applicationName + " received stock price update: " + price);
    }
}
class Implementing_ObserverPatternDemo {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();

        Observer mobileNotifier = new MobileNotification("MobileApp");
        Observer webNotifier = new WebNotification("WebApp");

        stockExchange.addObserver(mobileNotifier);
        stockExchange.addObserver(webNotifier);

        stockExchange.setStockPrice(100.00);
        stockExchange.setStockPrice(101.50);

        stockExchange.removeObserver(webNotifier);
        stockExchange.setStockPrice(102.75);
    }
}
