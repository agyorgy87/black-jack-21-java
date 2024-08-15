package hu.acsgyorgy.black.jack._1;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Card {

    private String cardType;

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

}
