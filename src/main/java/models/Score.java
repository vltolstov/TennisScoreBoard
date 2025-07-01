package models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Score {

    private int firstPlayerSets;
    private int firstPlayerGames;
    private int firstPlayerPoints;
    private int secondPlayerSets;
    private int secondPlayerGames;
    private int secondPlayerPoints;
    private boolean deuce;
    private boolean deuceGame;
    private boolean tieBreak;

    public void initializePlayersPoints(){
        setFirstPlayerPoints(0);
        setSecondPlayerPoints(0);
    }

    public void initializePlayersGames(){
        setFirstPlayerGames(0);
        setSecondPlayerGames(0);
    }

    public String getSetsScore(){
        return getFirstPlayerSets() + " - " + getSecondPlayerSets();
    }

}
