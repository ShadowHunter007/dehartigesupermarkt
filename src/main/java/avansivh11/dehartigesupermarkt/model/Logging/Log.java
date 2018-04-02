package avansivh11.dehartigesupermarkt.model.Logging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String logLine;

    @Column
    private Timestamp timestamp;

    public Log(String logLine){
        this.logLine = logLine;
        timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
