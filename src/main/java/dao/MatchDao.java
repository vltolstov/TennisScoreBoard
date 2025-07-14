package dao;

import models.Match;
import models.Player;

import java.util.List;

public interface MatchDao extends CrudDao<Match, Integer> {

    List<Match> findAll(int page, int pageSize);

    List<Match> findByPlayer(Player player, int page, int pageSize);

    Long getTotalMatchesCount();

    Long getTotalMatchesCountByPlayer(Player player);

}
