package hu.acsgyorgy.black.jack._1;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "black_jack")
@Getter
@Setter

public class Game {

    @Id
    @GeneratedValue

    private int id;

    private String playerName;

    private int turn;

    private int cardSum;

    private boolean finished;

    private String winner;

    private int enemyCardSum;

}
