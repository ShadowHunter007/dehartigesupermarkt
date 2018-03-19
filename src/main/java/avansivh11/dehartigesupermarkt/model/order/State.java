package avansivh11.dehartigesupermarkt.model.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class State {
    @GeneratedValue
    @Id
    private Long id;

    @NotNull(message = "leverancier mag niet leeggelaten worden")
    private String deliverer;

    @NotNull(message = "moment van ingang van deze staat mag niet leeggelaten worden")
    //add date regex
    private String entranceDate;

    @NotNull(message = "moment van uitgang uit deze staat mag niet leeggelaten worden")
    //do not forget to update when going to other state
    //add date regex
    private String exitDate;

    public abstract void goNext(BaseOrder order);
}
