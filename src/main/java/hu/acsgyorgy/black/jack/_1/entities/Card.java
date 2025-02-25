package hu.acsgyorgy.black.jack._1.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Card {

    private String cardType;

    private boolean pulledOut;

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

}
