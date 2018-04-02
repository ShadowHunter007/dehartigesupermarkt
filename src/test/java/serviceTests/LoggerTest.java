package serviceTests;

import avansivh11.dehartigesupermarkt.service.LoggingService;
import avansivh11.dehartigesupermarkt.service.logging.AbstractLogger;
import avansivh11.dehartigesupermarkt.service.logging.ConsoleLogger;
import avansivh11.dehartigesupermarkt.service.logging.FileLogger;
import avansivh11.dehartigesupermarkt.service.logging.WebsiteLogger;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class LoggerTest {

    @MockBean
    private LoggingService loggingService;

    @Test
    public void LogLevelTest(){
        AbstractLogger websiteLogger = new WebsiteLogger(AbstractLogger.WEBSITE, loggingService);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.FILE);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.CONSOLE);

        websiteLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        //Test level 3
        Assertions.assertThat(websiteLogger.nextLogger).isInstanceOf(FileLogger.class);
        Assertions.assertThat(websiteLogger.nextLogger.nextLogger).isInstanceOf(ConsoleLogger.class);
        Assertions.assertThat(websiteLogger.nextLogger.nextLogger.nextLogger == null);

        //Test level 2
        Assertions.assertThat(fileLogger.nextLogger).isInstanceOf(ConsoleLogger.class);
        Assertions.assertThat(fileLogger.nextLogger.nextLogger == null);

        //Test level 1
        Assertions.assertThat(consoleLogger.nextLogger == null);
    }
}
