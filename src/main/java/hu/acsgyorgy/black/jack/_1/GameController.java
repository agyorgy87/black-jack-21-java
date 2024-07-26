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
    public boolean createPlayer(@RequestBody Game game) {
        gameRepository.save(game);
        return true;
        //object return?
    }


    /*
    @GetMapping(
            path = "/get-player-data/{playerNickName}"
    )
    public Game searchByName(@PathVariable String playerNickName) {
        Optional<Game> game = GameRepository.findByNameContaining(playerNickName);
        if(game.isEmpty()) {
            //throw new IdNotFoundException("name not found");
            return null;
        }else{
            return game.get();
        }
    }
    */

    @GetMapping(path = "/get-player-data/{playerNickName}")
    public List<Game> findPlayerName(@PathVariable String playerNickName) {
        return GameRepository.findByPlayerName(playerNickName);
    }
