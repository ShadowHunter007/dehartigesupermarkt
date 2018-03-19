package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.Logging.Log;
import avansivh11.dehartigesupermarkt.repository.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoggingService {

    private final LoggingRepository loggingRepository;

    @Autowired
    public LoggingService(LoggingRepository loggingRepository){
        this.loggingRepository = loggingRepository;
    }

    public void addLogLine(Log logLine){
        this.loggingRepository.save(logLine);
    }

    public ArrayList<Log> getLog(){
        return (ArrayList<Log>) this.loggingRepository.findAll();
    }
}
