package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "player1")
    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player playerOne;

    @Column(name = "player2")
    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player playerTwo;

    @Embedded
    @JoinColumn(name = "score")
    private Score score = new Score();

    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;
}
