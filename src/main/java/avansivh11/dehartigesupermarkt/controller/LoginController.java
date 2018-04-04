package avansivh11.dehartigesupermarkt.controller;

import avansivh11.dehartigesupermarkt.model.account.Role;
import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping(value = "login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/login/login");
        return modelAndView;
    }


    @GetMapping(value = "registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("views/login/registration");
        return modelAndView;
    }

    @PostMapping(value = "registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        //loginService.createRole(new Role("CUSTOMER"));
        ModelAndView modelAndView = new ModelAndView();
        User userExists = loginService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Er bestaat al een account met het opgegeven emailadres.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("views/login/registration");
        } else {
            loginService.saveUser(user);
            modelAndView.addObject("successMessage", "Account is succesvol aangemaakt.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("views/login/login");

        }
        return modelAndView;
    }
}
