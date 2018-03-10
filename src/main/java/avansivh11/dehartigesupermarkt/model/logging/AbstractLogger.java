package avansivh11.dehartigesupermarkt.model.logging;

public abstract class AbstractLogger {
    public static int CONSOLE = 1;
    public static int FILE = 2;
    public static int WEBSITE = 3;

    int level;
    AbstractLogger nextLogger;

//    private void setNextLogger(AbstractLogger nextLogger){
//        this.nextLogger = nextLogger;
//    }

    public void logMessage(int level, String message){
        if(this.level <= level){
            write(message);
        }
        if(nextLogger != null){
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);

//    public static AbstractLogger getChainOfLoggers(){
//        AbstractLogger websiteLogger = new WebsiteLogger(WEBSITE);
//        AbstractLogger fileLogger = new FileLogger(FILE);
//        AbstractLogger consoleLogger = new ConsoleLogger(CONSOLE);
//
//        websiteLogger.setNextLogger(fileLogger);
//        fileLogger.setNextLogger(consoleLogger);
//
//        return websiteLogger;
//    }
}
