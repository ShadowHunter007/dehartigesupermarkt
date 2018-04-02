package avansivh11.dehartigesupermarkt.model.shoppingcart;

import avansivh11.dehartigesupermarkt.Security.CurrentUser;
import com.sun.management.VMOption;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Caretaker{
    private List<Memento> mementos;

    public Caretaker() {
        this.mementos = new ArrayList<>();
    }

    public void addMementoAtTail(Memento m) {
        mementos.add(m);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }

    public void removeMemento(int index) {
        mementos.remove(index);
    }
}
