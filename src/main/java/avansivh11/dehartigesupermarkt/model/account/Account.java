package avansivh11.dehartigesupermarkt.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

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

    @NotEmpty(message = "first name is required.")
    private String firstname;

    @NotEmpty(message = "last name is required.")
    private String lastname;

    @NotEmpty(message = "Please provide an email")
    private String email;

    @NotEmpty(message = "Please provide your password")
    @Length(min = 6, message = "Your password must have at least 6 characters")
    @Transient
    private String password;
}
