package hu.acsgyorgy.black.jack._1.respositories;
import hu.acsgyorgy.black.jack._1.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    Optional<Card> findById(int id);

}
