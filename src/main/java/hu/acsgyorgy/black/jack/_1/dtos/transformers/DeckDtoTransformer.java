package hu.acsgyorgy.black.jack._1.dtos.transformers;
import hu.acsgyorgy.black.jack._1.dtos.DeckDto;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckDtoTransformer {

    @Autowired
    private CardDtoTransformer cardDtoTransformer;

    public DeckDto transform (Deck deck) {
        DeckDto deckDto = new DeckDto();
        deckDto.setId(deck.getId());
        deckDto.setName(deck.getName());
        deckDto.setInGame(deck.isInGame());
        deckDto.setGameStarted(deck.getGameStarted());
        deckDto.setCards(cardDtoTransformer.transform(deck.getCards()));
        return deckDto;
    }

}
