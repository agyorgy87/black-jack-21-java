package hu.acsgyorgy.black.jack._1.controllers;
import hu.acsgyorgy.black.jack._1.dtos.CardDto;
import hu.acsgyorgy.black.jack._1.dtos.transformers.CardDtoTransformer;
import hu.acsgyorgy.black.jack._1.entities.Card;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import hu.acsgyorgy.black.jack._1.respositories.CardRepository;
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
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping(path = "/card/by-id/{id}")
    public ResponseEntity<CardDto> mainCard(@PathVariable int id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);//404
        } else {
            CardDtoTransformer cardDtoTransformer = new CardDtoTransformer();
            CardDto cardDto = cardDtoTransformer.transform(card.get());
            return ResponseEntity.ok(cardDto);
            //return ResponseEntity.ok(card.get());//200
            //return ResponseEntity.status(HttpStatus.OK).body(game.get());
        }
    }

}
