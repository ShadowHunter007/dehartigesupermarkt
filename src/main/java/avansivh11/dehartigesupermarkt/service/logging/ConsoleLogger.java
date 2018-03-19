package avansivh11.dehartigesupermarkt.service.logging;

public class ConsoleLogger extends AbstractLogger {

    ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message){
        System.out.println("Console::Logger: " + message);
    }
}
