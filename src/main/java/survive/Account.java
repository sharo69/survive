package survive;

import javax.persistence.*;

/**
 * Created by nikolakaloyanov on 6/25/16.
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    private Account(){}

    public Account(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
