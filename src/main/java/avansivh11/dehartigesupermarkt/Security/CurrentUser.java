package avansivh11.dehartigesupermarkt.Security;

import avansivh11.dehartigesupermarkt.model.account.User;
import avansivh11.dehartigesupermarkt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private AuthenticationFacade authenticationFacade;
    private LoginService loginService;

    @Autowired
    public CurrentUser(AuthenticationFacade authenticationFacade, LoginService loginService){
        this.authenticationFacade = authenticationFacade;
        this.loginService = loginService;
    }

    public User getCurrentUser(){
        Authentication authentication = authenticationFacade.getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            return loginService.findUserByEmail(email);
        }
        return null;
    }
}
