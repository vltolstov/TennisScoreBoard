import models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.MatchScoreService;

import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreServiceTest {

    private MatchScoreService matchScoreService;
    private Score score;
    private int firstPlayerId;
    private int secondPlayerId;
    private int winnerId;

    @BeforeEach
    public void setUp(){
        matchScoreService = new MatchScoreService();
        score = new Score();
        firstPlayerId = 1;
        secondPlayerId = 2;
    }

    @Test
    @DisplayName("Добавление 15 очков при первой победе")
    public void testCalculateFirstPoint(){
        winnerId = firstPlayerId;
        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);

        assertEquals(score.getFirstPlayerPoints(), 15);
    }

    @Test
    @DisplayName("Добавление 15 очков при второй победе")
    public void testCalculateSecondPoint(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(15);
        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);

        assertEquals(score.getFirstPlayerPoints(), 30);
    }

    @Test
    @DisplayName("Добавление 10 очков при третьей победе")
    public void testCalculateThirdPoint(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(30);
        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);

        assertEquals(score.getFirstPlayerPoints(), 40);
    }

    @Test
    @DisplayName("Обнуление счета при достижении счета ровно 40-40")
    public void testSetZeroPointsIfDeuce(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(30);
        score.setSecondPlayerPoints(40);
        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);

        assertEquals(score.getFirstPlayerPoints(), 0);
        assertEquals(score.getSecondPlayerPoints(), 0);
    }

    @Test
    @DisplayName("Если ровно, то добавление по 1 очку")
    public void testCalculateFirstPointIfDeuce(){
        winnerId = firstPlayerId;
        score.setDeuce(true);
        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);

        assertEquals(score.getFirstPlayerPoints(), 1);
        assertEquals(score.getSecondPlayerPoints(), 0);
    }

    @Test
    @DisplayName("Если ровно и счет 1 - 1, то сброс до 0 - 0")
    public void testResetScoreIfDeuceRepeat(){
        winnerId = secondPlayerId;
        score.setDeuce(true);
        score.setFirstPlayerPoints(1);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerPoints(), 0);
        assertEquals(score.getSecondPlayerPoints(), 0);
    }

    @Test
    @DisplayName("При ровно 2 - 0, победа в гейме первого игрока")
    public void testCalculateGameWinIfDeuce(){
        winnerId = firstPlayerId;
        score.setDeuce(true);
        score.setFirstPlayerPoints(1);
        score.setSecondPlayerPoints(0);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerGames(), 1);
        assertEquals(score.getSecondPlayerGames(), 0);
        assertEquals(score.getFirstPlayerPoints(), 0);
        assertEquals(score.getSecondPlayerPoints(), 0);
        assertFalse(score.isDeuce());
    }

    @Test
    @DisplayName("Победа в гейме при выйгрыше по очкам при счете 40 - 30")
    public void testCalculateGameWinIfDeuceRepeat(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(40);
        score.setSecondPlayerPoints(30);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerGames(), 1);
        assertEquals(score.getSecondPlayerGames(), 0);
        assertEquals(score.getFirstPlayerPoints(), 0);
        assertEquals(score.getSecondPlayerPoints(), 0);
        assertFalse(score.isDeuce());
    }

    @Test
    @DisplayName("Победа в сете при счете 6 - 4 по геймам")
    public void testCalculateSetWinForNormalGameScore(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(40);
        score.setFirstPlayerGames(5);
        score.setSecondPlayerGames(4);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerSets(), 1);
        assertEquals(score.getSecondPlayerSets(), 0);
        assertEquals(score.getFirstPlayerGames(), 0);
        assertEquals(score.getSecondPlayerGames(), 0);
    }

    @Test
    @DisplayName("Установка ничьи 5 - 5")
    public void testSetDeuceForGame(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(40);
        score.setFirstPlayerGames(4);
        score.setSecondPlayerGames(5);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertTrue(score.isDeuceGame());
    }

    @Test
    @DisplayName("Победа в сете при счете 7 - 5 по геймам")
    public void testCalculateSetWinForDeuceGameScore(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(40);
        score.setFirstPlayerGames(6);
        score.setSecondPlayerGames(5);
        score.setDeuceGame(true);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerSets(), 1);
        assertEquals(score.getSecondPlayerSets(), 0);
        assertEquals(score.getFirstPlayerGames(), 0);
        assertEquals(score.getSecondPlayerGames(), 0);
        assertEquals(score.isDeuceGame(), false);
    }

    @Test
    @DisplayName("Установка тай брейка при счете 6 - 6 по геймам")
    public void testSetTieBreak(){
        winnerId = secondPlayerId;
        score.setSecondPlayerPoints(40);
        score.setFirstPlayerGames(6);
        score.setSecondPlayerGames(5);
        score.setDeuceGame(true);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertTrue(score.isTieBreak());
    }

    @Test
    @DisplayName("Подсчет очков по 1 при тай брейке")
    public void testCalculatePointsIfTieBreak(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(3);
        score.setDeuce(true);
        score.setDeuceGame(true);
        score.setTieBreak(true);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerPoints(), 4);
    }

    @Test
    @DisplayName("Победа в гейме при тай брейке со счетом по очкам 7 - 1")
    public void testCalculateGameWinIfTieBreakHasNormalScore(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(6);
        score.setSecondPlayerPoints(1);
        score.setFirstPlayerGames(6);
        score.setSecondPlayerGames(6);
        score.setDeuce(true);
        score.setDeuceGame(true);
        score.setTieBreak(true);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerSets(), 1);
        assertTrue(score.getSecondPlayerGames() <= 4, "Разница в счете больше двух очков");
        assertEquals(score.getSecondPlayerSets(), 0);
    }

    @Test
    @DisplayName("Подсчет в тайбрейке со счетом более 7 - 7")
    public void testCalculatePointsIfTieBreakHasOverScore(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(7);
        score.setSecondPlayerPoints(7);
        score.setFirstPlayerGames(6);
        score.setSecondPlayerGames(6);
        score.setDeuce(true);
        score.setDeuceGame(true);
        score.setTieBreak(true);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerPoints(), 8);
        assertEquals(score.getSecondPlayerPoints(), 7);
        assertEquals(score.getFirstPlayerGames(), 6);
        assertEquals(score.getSecondPlayerGames(), 6);
        assertEquals(score.getSecondPlayerSets(), 0);
        assertEquals(score.getSecondPlayerSets(), 0);
    }

    @Test
    @DisplayName("Победа в тай брейке при счете более 7 - 7")
    public void testCalculateSetWinIfTieBreakHasOverScore(){
        winnerId = firstPlayerId;
        score.setFirstPlayerPoints(8);
        score.setSecondPlayerPoints(7);
        score.setFirstPlayerGames(6);
        score.setSecondPlayerGames(6);
        score.setDeuce(true);
        score.setDeuceGame(true);
        score.setTieBreak(true);

        matchScoreService.scoreCalculate(score, firstPlayerId, secondPlayerId, winnerId);
        assertEquals(score.getFirstPlayerPoints(), 0);
        assertEquals(score.getSecondPlayerPoints(), 0);
        assertEquals(score.getFirstPlayerGames(), 0);
        assertEquals(score.getSecondPlayerGames(), 0);
        assertEquals(score.getFirstPlayerSets(), 1);
        assertEquals(score.getSecondPlayerSets(), 0);
        assertFalse(score.isDeuce());
        assertFalse(score.isDeuceGame());
        assertFalse(score.isTieBreak());
    }


}
