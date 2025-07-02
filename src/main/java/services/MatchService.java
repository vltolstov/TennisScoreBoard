package services;

import models.Match;
import models.Score;

public class MatchService {

    public boolean checkGameOver(Match match) {
        return match.getScore().getFirstPlayerSets() == 2 || match.getScore().getSecondPlayerSets() == 2;
    }

    public void setWinner(Score score, Match match) {
        if(score.getFirstPlayerSets() == 2) {
            match.setWinner(match.getFirstPlayer());
        }
        if(score.getSecondPlayerSets() == 2) {
            match.setWinner(match.getSecondPlayer());
        }
    }

}
