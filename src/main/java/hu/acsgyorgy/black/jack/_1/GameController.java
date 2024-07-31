package hu.acsgyorgy.black.jack._1;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @PostMapping(
            path = "/api/game"
    )
    public Game createPlayer(@RequestBody Game game) {
        game = gameRepository.save(game);
        return game;
    }


    @GetMapping(
            path = "/get-game-id/{gameId}"
    )
    public Game searchById(@PathVariable int gameId ) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            //throw new IdNotFoundException("name not found");
            return null;
        } else {
            return game.get();
        }
    }
}
