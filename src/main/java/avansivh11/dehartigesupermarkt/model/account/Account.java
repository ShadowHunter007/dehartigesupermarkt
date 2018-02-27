package avansivh11.dehartigesupermarkt.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Username is required.")
    private String username;
}
