package hu.acsgyorgy.black.jack._1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
