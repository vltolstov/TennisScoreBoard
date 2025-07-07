package dao;

import models.Player;

import java.util.Optional;

public interface PlayerDao extends CrudDao<Player, Integer> {

    Optional<Player> findByName(String name);

}
