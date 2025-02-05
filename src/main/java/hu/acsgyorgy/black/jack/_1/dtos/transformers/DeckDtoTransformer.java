package hu.acsgyorgy.black.jack._1.dtos.transformers;
import hu.acsgyorgy.black.jack._1.dtos.DeckDto;
import hu.acsgyorgy.black.jack._1.entities.Deck;

import java.util.ArrayList;
import java.util.List;

public class DeckDtoTransformer {
    private CardDtoTransformer cardDtoTransformer = new CardDtoTransformer();

    public DeckDto transform (Deck deck) {
        DeckDto deckDto = new DeckDto();
        deckDto.setId(deck.getId());
        deckDto.setName(deck.getName());
        deckDto.setCards(cardDtoTransformer.transform(deck.getCards()));
        return deckDto;
    }

}
