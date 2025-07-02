package services;

import models.Match;
import models.Score;

public class MatchService {

    public void setWinner(Score score, Match match) {
        if(score.getFirstPlayerSets() == 2) {
            match.setWinner(match.getFirstPlayer());
        }
        if(score.getSecondPlayerSets() == 2) {
            match.setWinner(match.getSecondPlayer());
        }
    }

}
