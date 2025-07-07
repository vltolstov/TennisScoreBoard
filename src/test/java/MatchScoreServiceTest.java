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
        assertEquals(score.isDeuce(), false);
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
        assertEquals(score.isDeuce(), false);
    }
}
