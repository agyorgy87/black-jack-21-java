package hu.acsgyorgy.black.jack._1.respositories;


import hu.acsgyorgy.black.jack._1.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck,Integer> {

    Optional<Deck> findById(int id);

}
