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

    private int playerOneSets;
    private int playerTwoSets;
    private int playerOneGames;
    private int playerTwoGames;
    private int playerOnePoints;
    private int playerTwoPoints;

}
