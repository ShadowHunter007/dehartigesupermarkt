package avansivh11.dehartigesupermarkt.service.logging;

import avansivh11.dehartigesupermarkt.model.Logging.Log;
import avansivh11.dehartigesupermarkt.service.LoggingService;

public class WebsiteLogger extends AbstractLogger {

    private final LoggingService loggingService;

    WebsiteLogger(int level, LoggingService loggingService){
        this.level = level;
        this.loggingService = loggingService;
    }

    @Override
    protected void write(String message) {
        loggingService.addLogLine(new Log(message));
    }
}
