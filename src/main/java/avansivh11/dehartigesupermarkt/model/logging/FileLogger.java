package avansivh11.dehartigesupermarkt.model.logging;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
        this.nextLogger = new ConsoleLogger(CONSOLE);
    }

    @Override
    protected void write(String message) {
        //TODO implement correct behaviour
        System.out.println("File::Logger: " + message);
    }
}
