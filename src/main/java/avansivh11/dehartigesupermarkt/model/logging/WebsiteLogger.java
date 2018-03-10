package avansivh11.dehartigesupermarkt.model.logging;

public class WebsiteLogger extends AbstractLogger {

    public WebsiteLogger(int level){
        this.level = level;
        this.nextLogger = new FileLogger(FILE);
    }

    @Override
    protected void write(String message) {
        //TODO implement correct behaviour
        System.out.println("Website::Logger: " + message);
    }
}
