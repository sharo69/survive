package survive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nikolakaloyanov on 6/25/16.
 */
@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int x;

    @Column
    private int y;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Resource> resources;

    private Zone() {
    }

    public Zone(int x, int y) {
        this.x = x;
        this.y = y;
        createResources();
    }

    private void createResources() {
        resources = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 3; i++)
            if (r.nextBoolean()) {
                resources.add(new Resource(r.nextInt(100) + 1, r.nextInt(100) + 1));
            }
    }

    public long getId() {
        return id;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
