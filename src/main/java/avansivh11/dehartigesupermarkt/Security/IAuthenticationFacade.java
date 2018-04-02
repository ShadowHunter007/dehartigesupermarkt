package avansivh11.dehartigesupermarkt.Security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}

