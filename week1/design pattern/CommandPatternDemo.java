// Define the Command interface
interface Action {
    void execute();
}

// Implement concrete command classes
class TurnOnLightCommand implements Action {
    private Light lightSource;

    public TurnOnLightCommand(Light lightSource) {
        this.lightSource = lightSource;
    }

    @Override
    public void execute() {
        lightSource.turnOn();
    }
}

class TurnOffLightCommand implements Action {
    private Light lightSource;

    public TurnOffLightCommand(Light lightSource) {
        this.lightSource = lightSource;
    }

    @Override
    public void execute() {
        lightSource.turnOff();
    }
}

// Implement the receiver class
class Light {
    public void turnOn() {
        System.out.println("The light is now on.");
    }

    public void turnOff() {
        System.out.println("The light is now off.");
    }
}

// Implement the invoker class
class Remote {
    private Action actionCommand;

    public void setAction(Action actionCommand) {
        this.actionCommand = actionCommand;
    }

    public void press() {
        actionCommand.execute();
    }
}

// Test the command pattern implementation
class CommandPatternDemo {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();

        Action lightOnCommand = new TurnOnLightCommand(livingRoomLight);
        Action lightOffCommand = new TurnOffLightCommand(livingRoomLight);

        Remote remote = new Remote();

        // Turn the light on
        remote.setAction(lightOnCommand);
        remote.press();

        // Turn the light off
        remote.setAction(lightOffCommand);
        remote.press();
    }
}
