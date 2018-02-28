package avansivh11.dehartigesupermarkt.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class BaseOrder {
    @Id
    @GeneratedValue
    private Long id;
}
