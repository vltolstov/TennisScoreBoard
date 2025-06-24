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

}
