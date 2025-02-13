package hu.acsgyorgy.black.jack._1.respositories;
import hu.acsgyorgy.black.jack._1.entities.Card;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    Optional<Card> findById(int id);

    List<Card> findAllByPulledOutFalseAndDeck(Deck deck);

}
