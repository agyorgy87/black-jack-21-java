package hu.acsgyorgy.black.jack._1.controllers;

import hu.acsgyorgy.black.jack._1.dtos.CardDto;
import hu.acsgyorgy.black.jack._1.dtos.DeckDto;
import hu.acsgyorgy.black.jack._1.dtos.transformers.CardDtoTransformer;
import hu.acsgyorgy.black.jack._1.entities.Card;
import hu.acsgyorgy.black.jack._1.respositories.CardRepository;
import hu.acsgyorgy.black.jack._1.respositories.DeckRepository;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DeckController {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardDtoTransformer cardDtoTransformer;

    @GetMapping(path = "/deck/by-id/{id}")
    public ResponseEntity<Deck> mainDeck(@PathVariable int id) {
        Optional<Deck> deck = deckRepository.findById(id);
        if (deck.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);//404
        } else {
            return ResponseEntity.ok(deck.get());//200
            //return ResponseEntity.status(HttpStatus.OK).body(game.get());
        }
    }

    public String randomCardName() {

        String[] adjectives = {
                "happy", "sad", "angry", "excited", "brave", "calm", "bold", "shy", "kind", "cruel",
                "strong", "weak", "fast", "slow", "smart", "dumb", "rich", "poor", "hardworking", "lazy",
                "funny", "serious", "friendly", "rude", "loyal", "unfaithful", "honest", "dishonest", "gentle", "harsh",
                "bright", "dark", "warm", "cold", "soft", "hard", "smooth", "rough", "clean", "dirty",
                "beautiful", "ugly", "tall", "short", "big", "small", "thin", "fat", "narrow", "wide",
                "loud", "quiet", "early", "late", "new", "old", "modern", "ancient", "fresh", "stale",
                "sweet", "bitter", "salty", "sour", "spicy", "bland", "tasty", "disgusting", "hungry", "full",
                "strong-willed", "weak-minded", "courageous", "fearful", "ambitious", "lazy", "grateful", "ungrateful", "optimistic", "pessimistic",
                "creative", "unimaginative", "generous", "selfish", "reliable", "unreliable", "polite", "impolite", "cheerful", "gloomy",
                "energetic", "tired", "helpful", "useless", "productive", "wasteful", "organized", "messy", "determined", "indecisive"
        };

        String[] nouns = {
                "apple", "banana", "car", "dog", "elephant", "flower", "guitar", "house", "island", "jacket",
                "kangaroo", "lamp", "mountain", "notebook", "ocean", "pencil", "queen", "river", "sun", "tree",
                "umbrella", "violin", "window", "xylophone", "yacht", "zebra", "book", "chair", "desk", "egg",
                "forest", "garden", "hat", "ice", "jungle", "kite", "lion", "moon", "nest", "octopus",
                "piano", "quilt", "robot", "sandwich", "turtle", "unicorn", "volcano", "whale", "x-ray", "yogurt",
                "zeppelin", "airport", "bridge", "candle", "diamond", "engine", "fountain", "globe", "hammer", "igloo",
                "jewel", "key", "ladder", "mirror", "necklace", "orchestra", "palace", "quicksand", "rainbow", "suitcase",
                "ticket", "underpass", "vase", "waterfall", "xenon", "yard", "zeppelin", "alligator", "butterfly", "chocolate",
                "dolphin", "earthquake", "fireworks", "grapefruit", "hamburger", "iceberg", "jigsaw", "kitchen", "lighthouse", "mermaid",
                "nightingale", "oatmeal", "penguin", "quasar", "rocket", "snowflake", "treasure", "universe", "vulture", "windmill"
        };

        Random random = new Random();
        String adjective = adjectives[random.nextInt(adjectives.length)];
        String noun = nouns[random.nextInt(nouns.length)];

        return adjective + " " + noun;
    }

    @PostMapping(path = "/deck/create")
    public ResponseEntity<DeckDto> createDeck() {
        DeckDto deckDto = new DeckDto();
        Deck deck = new Deck();

        String randomName = randomCardName();
        deck.setName(randomName);

        List<Card> cards = new ArrayList<>();
        String[] types = {"heart", "spade", "club", "diamond"};
        String[] numbers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String type : types) {
            for (String number : numbers) {
                Card card = new Card();
                card.setCardType(type + "_" + number);
                card.setDeck(deck);
                cards.add(card);
            }
        }


        Date startDate = new Date();
        deck.setGameStarted(startDate);
        deck.setCards(cards);
        deck.setInGame(true);
        deckRepository.save(deck);

        deckDto.setId(deck.getId());
        deckDto.setName(deck.getName());
        deckDto.setGameStarted(deck.getGameStarted());
        deckDto.setInGame(deck.isInGame());

        List<CardDto> CardDtoList = cardDtoTransformer.transform(cards);
        deckDto.setCards(CardDtoList);

        return ResponseEntity.ok(deckDto);
    }
}
