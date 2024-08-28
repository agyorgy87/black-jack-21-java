package hu.acsgyorgy.black.jack._1.controllers;

import hu.acsgyorgy.black.jack._1.respositories.DeckRepository;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DeckController {

    @Autowired
    private DeckRepository deckRepository;

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
}
