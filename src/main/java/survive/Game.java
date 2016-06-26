package survive;

import javax.persistence.*;
import java.util.*;

/**
 * Created by nikolakaloyanov on 6/25/16.
 */
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int maxPlayers;

    @Column
    private int width;

    @Column
    private int height;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Zone> zones;

    private Game() {
    }

    public Game(int maxPlayers, int width, int height) {
        this.maxPlayers = maxPlayers;
        this.width = width;
        this.height = height;
        players = new LinkedList<>();
        createZones();
    }

    private void createZones() {
        zones = new LinkedList<>();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                zones.add(new Zone(i, j));
            }
    }

    public Zone findZone(int x, int y) {
        for (Zone z : zones)
            if (x == z.getX() && y == z.getY()) {
                return z;
            }

        return null;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public long getId() {
        return id;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Zone> getZones() {
        return zones;
    }
}
