package avansivh11.dehartigesupermarkt.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "Please provide a valid Email")
    @NotEmpty(message = "Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "Your password must have at least 5 characters")
    @NotEmpty(message = "Please provide your password")
    //@Transient
    private String password;

    @Column(name = "username")
    @NotEmpty(message = "Please provide your username")
    private String username;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public boolean isActive() {
        return active;
    }

   // public void setActive(boolean active) {
    //    this.active = active;
    //}

    //public Set<Role> getRoles() {
    //    return roles;
    //}

    //public void setRoles(Set<Role> roles) {
    //    this.roles = roles;
    //}


}
