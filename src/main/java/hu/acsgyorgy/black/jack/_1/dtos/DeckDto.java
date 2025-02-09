package hu.acsgyorgy.black.jack._1.dtos;

import hu.acsgyorgy.black.jack._1.entities.Card;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DeckDto {

    private String name;

    private int id;

    private Date gameStarted;

    private boolean inGame;

    private List<CardDto> cards;

}
