package dao;

import models.Match;
import models.Player;

import java.util.List;
import java.util.Optional;

public interface MatchDao extends CrudDao<Match, Integer> {

    Optional<Match> findById(Integer id);

    List<Match> findByPlayer(Player player);

}
