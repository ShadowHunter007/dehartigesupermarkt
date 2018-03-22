package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.Logging.Log;
import avansivh11.dehartigesupermarkt.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/logging")
public class LoggingController {

    private final LoggingService loggingService;

    @Autowired
    public LoggingController(LoggingService loggingService){
        this.loggingService = loggingService;
    }

    @GetMapping("/log")
    public String log(Model model){

        Iterable<Log> log = loggingService.getLog();
        model.addAttribute("log", log);

        return "views/log/log";
    }
}
