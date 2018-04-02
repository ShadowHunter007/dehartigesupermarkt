package avansivh11.dehartigesupermarkt.service.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        Path file = CreateFileSingleton.getInstance().getFile();
        String logLine = message + " Timestamp: " + LocalDateTime.now().toLocalTime().toString() + "\n";

        try {
            Files.write(file, logLine.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
