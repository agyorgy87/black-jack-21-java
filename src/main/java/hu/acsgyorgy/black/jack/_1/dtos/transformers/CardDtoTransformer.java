package hu.acsgyorgy.black.jack._1.dtos.transformers;
import hu.acsgyorgy.black.jack._1.dtos.CardDto;
import hu.acsgyorgy.black.jack._1.entities.Card;

import java.util.ArrayList;
import java.util.List;


public class CardDtoTransformer {

    public CardDto transform (Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setCardType(card.getCardType());
        return cardDto;
    }

    public List<CardDto> transform (List<Card> cardList) {
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card listElement : cardList) {
            CardDto cardDto = transform(listElement);
            cardDtoList.add(cardDto);
        }
        return cardDtoList;
    }

}
