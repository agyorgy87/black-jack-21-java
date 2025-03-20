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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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

    //.

    @PostMapping(path = "/deck/create/{deckName}")
    public ResponseEntity<DeckDto> createDeck() {
        DeckDto deckDto = new DeckDto();
        Deck deck = new Deck();
        //függvény hivás
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
