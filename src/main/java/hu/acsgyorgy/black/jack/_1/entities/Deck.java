package hu.acsgyorgy.black.jack._1.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Deck {

    private String name;

    private Date gameStarted;

    private boolean inGame;

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "deck")
    private List<Player> player;
}
