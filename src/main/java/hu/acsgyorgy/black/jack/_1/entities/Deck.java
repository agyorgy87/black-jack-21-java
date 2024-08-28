package hu.acsgyorgy.black.jack._1.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Deck {

    private String name;

    @Id
    private int id;

    @OneToMany(mappedBy = "deck")
    private List<Card> cards;

}
