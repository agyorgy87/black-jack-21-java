package hu.acsgyorgy.black.jack._1.respositories;
import hu.acsgyorgy.black.jack._1.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {

    Optional<Game> findById(int id);
}
