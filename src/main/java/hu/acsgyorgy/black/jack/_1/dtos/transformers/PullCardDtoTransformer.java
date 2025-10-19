package hu.acsgyorgy.black.jack._1.dtos.transformers;

import hu.acsgyorgy.black.jack._1.dtos.PullCardDto;
import hu.acsgyorgy.black.jack._1.entities.Deck;
import hu.acsgyorgy.black.jack._1.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PullCardDtoTransformer {

    public PullCardDto transform (Deck deck) {
        PullCardDto pullCardDtoDeckId = new PullCardDto();
        pullCardDtoDeckId.setDeckId(deck.getId());
        return pullCardDtoDeckId;
    }

    public PullCardDto transform (Game game) {
        PullCardDto pullCardDtoGameId = new PullCardDto();
        pullCardDtoGameId.setGameId(game.getId());
        return pullCardDtoGameId;
    }
}
