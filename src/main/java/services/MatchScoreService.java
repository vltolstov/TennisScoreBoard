package services;

import models.Match;
import models.Player;
import models.Score;

public class MatchScoreService {

    public void scoreCalculate(Score score, int firstPlayerId, int secondPlayerId, int pointWinnerId) {

        //подсчет очков по одному при ровно или геймах 6 - 6
        if(score.isDeuce()) {
            if(pointWinnerId == firstPlayerId) {
                score.setFirstPlayerPoints(score.getFirstPlayerPoints() + 1);
            }

            if(pointWinnerId == secondPlayerId) {
                score.setSecondPlayerPoints(score.getSecondPlayerPoints() + 1);
            }

        //подсчет очков при стандартном счете
        } else {
            if(pointWinnerId == firstPlayerId) {
                if(score.getFirstPlayerPoints() == 0 || score.getFirstPlayerPoints() == 15) {
                    score.setFirstPlayerPoints(score.getFirstPlayerPoints() + 15);
                } else {
                    score.setFirstPlayerPoints(score.getFirstPlayerPoints() + 10);
                }
            } else {
                if(score.getSecondPlayerPoints() == 0 || score.getSecondPlayerPoints() == 15) {
                    score.setSecondPlayerPoints(score.getSecondPlayerPoints() + 15);
                } else {
                    score.setSecondPlayerPoints(score.getSecondPlayerPoints() + 10);
                }
            }
            if(score.getFirstPlayerPoints() == 40 && score.getSecondPlayerPoints() == 40) {
                score.setDeuce(true);
                score.initializePlayersPoints();
            }
        }

        //условие победы в гейме при стандартном счете до 40
        if(score.getFirstPlayerPoints() > 40 || score.getSecondPlayerPoints() > 40) {
            if(pointWinnerId == firstPlayerId) {
                score.setFirstPlayerGames(score.getFirstPlayerGames() + 1);
            } else {
                score.setSecondPlayerGames(score.getSecondPlayerGames() + 1);
            }
            score.initializePlayersPoints();
        }


        //условия победы в сете при тай брейке до 7 или на 2 очка больше противника
        if(score.isTieBreak()){
            if(score.getFirstPlayerPoints() >= 7 && score.getSecondPlayerPoints() <= score.getFirstPlayerPoints() - 2) {
                        score.setFirstPlayerSets(score.getFirstPlayerSets() + 1);
                        score.setTieBreak(false);
            }
            if(score.getSecondPlayerPoints() >= 7 && score.getFirstPlayerPoints() <= score.getSecondPlayerPoints() - 2) {
                        score.setSecondPlayerSets(score.getSecondPlayerSets() + 1);
                        score.setTieBreak(false);
            }
            if(!score.isTieBreak()){
                score.setDeuce(false);
                score.setDeuceGame(false);
                score.initializePlayersPoints();
                score.initializePlayersGames();
            }

        //условие победы в геймах при ровно
        } else {
            if (score.getFirstPlayerPoints() == 2 && score.getSecondPlayerPoints() == 0) {
                score.setFirstPlayerGames(score.getFirstPlayerGames() + 1);
                score.initializePlayersPoints();
                score.setDeuce(false);
            } else if (score.getSecondPlayerPoints() == 2 && score.getFirstPlayerPoints() == 0) {
                score.setSecondPlayerGames(score.getSecondPlayerGames() + 1);
                score.initializePlayersPoints();
                score.setDeuce(false);
            } else if (score.getFirstPlayerPoints() == 1 && score.getSecondPlayerPoints() == 1) {
                score.initializePlayersPoints();
            }
        }

        //ничья по геймам при счете 5 - 5
        if( score.getFirstPlayerGames() == 5 && score.getSecondPlayerGames() == 5) {
            score.setDeuceGame(true);
        }

        //условия победы в сете при счете геймов 7 - 5 и установка тай брейка при 6 - 6
        if(score.isDeuceGame()){
            if (score.getFirstPlayerGames() == 7 && score.getSecondPlayerGames() == 5) {
                score.setFirstPlayerSets(score.getFirstPlayerSets() + 1);
                score.initializePlayersGames();
                score.setDeuceGame(false);
            }
            if (score.getSecondPlayerGames() == 7 && score.getFirstPlayerGames() == 5) {
                score.setSecondPlayerSets(score.getSecondPlayerSets() + 1);
                score.initializePlayersGames();
                score.setDeuceGame(false);
            }
            if(score.getFirstPlayerGames() == 6 && score.getSecondPlayerGames() == 6) {
                score.setDeuce(true);
                score.setTieBreak(true);
            }
        }

        //условия победы в сете при стандартном счете
        if(score.getFirstPlayerGames() == 6 && score.getSecondPlayerGames() <= 4) {
            score.setFirstPlayerSets(score.getFirstPlayerSets() + 1);
            score.initializePlayersGames();
        }
        if (score.getSecondPlayerGames() == 6 && score.getFirstPlayerGames() <= 4) {
            score.setSecondPlayerSets(score.getSecondPlayerSets() + 1);
            score.initializePlayersGames();
        }

    }

}
