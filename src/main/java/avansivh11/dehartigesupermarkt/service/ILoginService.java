package avansivh11.dehartigesupermarkt.service;

import avansivh11.dehartigesupermarkt.model.account.User;

public interface ILoginService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
