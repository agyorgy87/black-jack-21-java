package hu.acsgyorgy.black.jack._1.dtos.transformers;

import hu.acsgyorgy.black.jack._1.dtos.CardDto;
import hu.acsgyorgy.black.jack._1.entities.Card;

public class CardDtoTransformer {

    public CardDto transform (Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setCardType(card.getCardType());
        cardDto.setDeckName(card.getDeck().getName());
        return cardDto;
    }

}
