package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public abstract class OrderState {
    @GeneratedValue
    @Id
    protected Long id;

    //@Transient
    protected String statusName;

    @NotNull(message = "leverancier mag niet leeggelaten worden")
    protected String deliverer;

    @NotNull(message = "moment van ingang van deze staat mag niet leeggelaten worden")
    //add date regex
    protected String entranceDate;

    @NotNull(message = "moment van uitgang uit deze staat mag niet leeggelaten worden")
    //do not forget to update when going to other currentState
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")
    protected String exitDate;

    @NotNull(message="Vul een geldige baseorder in voor een toestand")
    @OneToOne
    protected BaseOrder context;

    public OrderState(BaseOrder context) {
        this.context = context;
    }

    public abstract void goNext(BaseOrder order);
}
