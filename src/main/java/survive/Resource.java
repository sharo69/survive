package survive;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

/**
 * Created by nikolakaloyanov on 6/25/16.
 */
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int hunger;

    @Column
    private int thirst;

    private Resource (){}

    public Resource(int hunger, int thirst) {
        this.hunger = hunger;
        this.thirst = thirst;
    }

    public long getId() {
        return id;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }
}
