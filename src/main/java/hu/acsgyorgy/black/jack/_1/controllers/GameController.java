package hu.acsgyorgy.black.jack._1.controllers;
import hu.acsgyorgy.black.jack._1.dtos.CardDto;
import hu.acsgyorgy.black.jack._1.dtos.PullCardDto;
import hu.acsgyorgy.black.jack._1.dtos.transformers.CardDtoTransformer;
import hu.acsgyorgy.black.jack._1.entities.Card;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import hu.acsgyorgy.black.jack._1.entities.Game;
import hu.acsgyorgy.black.jack._1.respositories.CardRepository;
import hu.acsgyorgy.black.jack._1.respositories.DeckRepository;
import hu.acsgyorgy.black.jack._1.respositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GameController {

    @Autowired
    private CardDtoTransformer cardDtoTransformer;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private GameRepository gameRepository;

    @PostMapping(
            path = "/api/game"
    )
    public Game createPlayer(@RequestBody Game game) {
        game = gameRepository.save(game);
        return game;
    }

/*
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
*/

    @GetMapping(path = "/get-game-id/{gameId}")
    public ResponseEntity<Game> searchById(@PathVariable int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);//404
        } else {
            return ResponseEntity.ok(game.get());//200
            //return ResponseEntity.status(HttpStatus.OK).body(game.get());
        }
    }

    @PostMapping(path = "/game/pull-unpulled-card")
    public ResponseEntity<CardDto> unpulledCard(@RequestBody PullCardDto pullCardDto) {
        Optional<Deck> deck = deckRepository.findById(pullCardDto.getDeckId());
        Optional<Game> gameObj = gameRepository.findById(pullCardDto.getGameId());
        /*
        System.out.println("Received deckId: " + pullCardDto.getDeckId());
        System.out.println("Received gameId: " + pullCardDto.getGameId());

        System.out.println("Deck found: " + deck.isPresent());
        System.out.println("Game found: " + gameObj.isPresent());

        if (!deck.isPresent() || !gameObj.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // vagy ResponseEntity.badRequest().build();
        }
        */
        List<Card> cards = cardRepository.findAllByPulledOutFalseAndDeck(deck.get());
        Random random = new Random();
        Card randomCard = cards.get(random.nextInt(cards.size()));
        randomCard.setPulledOut(true);
        CardDto cardDto = cardDtoTransformer.transform(randomCard);
        cardRepository.save(randomCard);
        int randomCardValue = this.convertToInt(randomCard.getCardType());
        int currentlySum = gameObj.get().getCardSum();
        gameObj.get().setCardSum(currentlySum + randomCardValue);
        gameRepository.save(gameObj.get());

        return ResponseEntity.ok(cardDto);
    }

    @PostMapping(path = "/hit-card/{gameId}")
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

    @GetMapping(path = "/show-cards/{gameId}")
    public ResponseEntity<Game> showAllCard(@PathVariable int gameId) {
        Optional<Game> game = gameRepository.findById(gameId);
        if(game.isPresent()) {
            Game setGameOver = game.get();
            int randomCardNumber = (int)(Math.random() * 21) + 1;
            int enemyCard;
            if(randomCardNumber < 16){
                enemyCard = randomCardNumber + ((int)(Math.random() * 11) + 1);
            } else {
                enemyCard = randomCardNumber;
            }
            int playerCardSum = game.get().getCardSum();
            if(playerCardSum < 22 && enemyCard > 21) {
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

    private Integer convertToInt (String cardValue) {
        String[] array = cardValue.split("_");
        String cardNumber = array[1];
        if(cardNumber.equals("2")){
            return 2;
        } else if(cardNumber.equals("3")){
            return 3;
        } else if(cardNumber.equals("4")){
            return 4;
        } else if(cardNumber.equals("5")){
            return 5;
        } else if(cardNumber.equals("6")){
            return 6;
        } else if(cardNumber.equals("7")){
            return 7;
        } else if(cardNumber.equals("8")){
            return 8;
        } else if(cardNumber.equals("9")){
            return 9;
        } else if(cardNumber.equals("10")){
            return 10;
        } else if(cardNumber.equals("J")){
            return 10;
        } else if(cardNumber.equals("Q")){
            return 10;
        } else if(cardNumber.equals("K")){
            return 10;
        } else if(cardNumber.equals("A")){
            return 11;
        }
        return 0;
    }

/*
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
    */
}

