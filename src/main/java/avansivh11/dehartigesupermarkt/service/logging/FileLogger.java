package avansivh11.dehartigesupermarkt.service.logging;

public class FileLogger extends AbstractLogger {

    FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        //TODO implement correct behaviour
        System.out.println("File::Logger: " + message);
    }
}
