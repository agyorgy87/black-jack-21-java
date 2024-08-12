package hu.acsgyorgy.black.jack._1;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Game searchById(@PathVariable int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            //throw new IdNotFoundException("name not found");
            return null;
        } else {
            return game.get();
        }
    }

    @PostMapping(
            path = "/hit-card/{gameId}"
    )
    public ResponseEntity<Game> hitCard(@PathVariable int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        int randomCardNumber = (int)(Math.random() * 11) + 1;
        if(game.isPresent()) {
            Game modifiedGame = game.get();
            modifiedGame.setTurn(modifiedGame.getTurn() + 1);
            modifiedGame.setCardSum(modifiedGame.getCardSum() + randomCardNumber);
            gameRepository.save(modifiedGame);
            return ResponseEntity.ok(modifiedGame);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(
            path = "/show-cards/{gameId}"
    )
    public ResponseEntity<Game> showAllCard(@PathVariable int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if(game.isPresent()) {
            Game setGameOver = game.get();
            int enemyCard = (int)(Math.random() * 40) + 1;
            //int randomCardNumber = (int)(Math.random() * 40) + 1;
            /*
            int enemyCard;
            if(randomCardNumber < 16){
                enemyCard = randomCardNumber + ((int)(Math.random() * 11) + 1);
            } else {
                enemyCard = randomCardNumber;
            }
             */
            int playerCardSum = game.get().getCardSum();
            if(playerCardSum < 21 && enemyCard > 21) {
                setGameOver.setWinner("Player winner");
            } else if (enemyCard < 21 && playerCardSum > 21) {
                setGameOver.setWinner("Enemy Winner");
            } else if(playerCardSum > enemyCard && playerCardSum < 21) {
                setGameOver.setWinner("Player winner");
            } else if(enemyCard > playerCardSum && enemyCard < 21) {
                setGameOver.setWinner("Enemy winner");
            } else {
                setGameOver.setWinner("Draw");
            }
            setGameOver.setFinished(true);
            setGameOver.setEnemyCardSum(enemyCard);
            gameRepository.save(setGameOver);
            return ResponseEntity.ok(setGameOver);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(
            path = "/game-over/"
    )
    public Game gameOver(@PathVariable int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        Game setGameOver = game.get();
        setGameOver.setFinished(true);
        gameRepository.save(setGameOver);
        return setGameOver;
    }

}

/*
            if(enemyCard > 21) {
                setGameOver.setWinner("Player winner");
            } else if(playerCardSum > enemyCard) {
                setGameOver.setWinner("Player winner");
            } else if(enemyCard > playerCardSum) {
                setGameOver.setWinner("Enemy winner");
            } else {
                setGameOver.setWinner("Draw");
            }
 */