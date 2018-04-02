package serviceTests;

import avansivh11.dehartigesupermarkt.service.LoggingService;
import avansivh11.dehartigesupermarkt.service.logging.AbstractLogger;
import avansivh11.dehartigesupermarkt.service.logging.ConsoleLogger;
import avansivh11.dehartigesupermarkt.service.logging.FileLogger;
import avansivh11.dehartigesupermarkt.service.logging.WebsiteLogger;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Test
    public void FileLoggerTest() throws IOException {
        //Create part of the chain
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.FILE);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.CONSOLE);
        fileLogger.setNextLogger(consoleLogger);

        //Log the message
        String message = "This is a file write test.";
        int messageLength = message.length();
        fileLogger.logMessage(AbstractLogger.FILE, message);

        //Get current date for file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());

        //Try to read the last line of the file
        BufferedReader input = new BufferedReader(new FileReader("src/output/Log"+ timeStamp +".txt"));
        String line,last = "";
        while ((line = input.readLine()) != null) {
            last = line;
        }

        //The last line contains a timestamp. We only want to test the message itself
        Assert.assertEquals(last.substring(0,messageLength),message);
    }
}
