interface NotificationService {
    void deliver(String message);
}

class EmailNotification implements NotificationService {
    @Override
    public void deliver(String message) {
        System.out.println("Delivering email notification: " + message);
    }
}

abstract class NotificationDecorator implements NotificationService {
    protected NotificationService wrappedNotifier;

    public NotificationDecorator(NotificationService notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void deliver(String message) {
        wrappedNotifier.deliver(message);
    }
}

class SMSNotificationDecorator extends NotificationDecorator {
    public SMSNotificationDecorator(NotificationService notifier) {
        super(notifier);
    }

    @Override
    public void deliver(String message) {
        wrappedNotifier.deliver(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("Delivering SMS notification: " + message);
    }
}

class SlackNotificationDecorator extends NotificationDecorator {
    public SlackNotificationDecorator(NotificationService notifier) {
        super(notifier);
    }

    @Override
    public void deliver(String message) {
        wrappedNotifier.deliver(message);
        sendSlack(message);
    }

    private void sendSlack(String message) {
        System.out.println("Delivering Slack notification: " + message);
    }
}
class DecoratorPatternDemo {
    public static void main(String[] args) {
        NotificationService emailNotifier = new EmailNotification();
        NotificationService smsEnhancedNotifier = new SMSNotificationDecorator(emailNotifier);
        NotificationService slackEnhancedNotifier = new SlackNotificationDecorator(smsEnhancedNotifier);
        slackEnhancedNotifier.deliver("Hello, this is a test notification!");
    }
}
