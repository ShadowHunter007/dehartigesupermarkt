package avansivh11.dehartigesupermarkt.repository;

import avansivh11.dehartigesupermarkt.model.account.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);

}
