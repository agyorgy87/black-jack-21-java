package hu.acsgyorgy.black.jack._1;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Deck {

    @Id
    private int id;

    @OneToMany(mappedBy = "deck")
    private List<Card> cards;
}
