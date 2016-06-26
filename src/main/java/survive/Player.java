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
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int hunger;

    @Column
    private int thirst;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    private Zone currentZone;

    @ManyToMany
    private List<Zone> visitedZones;

    private Player(){}

    public Player(Zone currentZone) {
        this.hunger = 100;
        this.thirst = 100;
        this.currentZone = currentZone;
        account = new Account("Pe6ohakera_"+System.currentTimeMillis());
        visitedZones=new ArrayList<>();
    }

    public void addVisitedZone(Zone z){
        visitedZones.add(z);
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

    public Account getAccount() {
        return account;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public List<Zone> getVisitedZones() {
        return visitedZones;
    }

    public void setCurrentZone(Zone currentZone) {
        this.currentZone = currentZone;
    }
}
