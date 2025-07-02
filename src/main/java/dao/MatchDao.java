package dao;

import models.Match;
import models.Player;

import java.util.List;

public interface MatchDao extends CrudDao<Match, Integer> {

    List<Match> findByPlayer(Player player);

}
