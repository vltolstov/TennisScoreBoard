package examples;

import dao.HibernatePlayerDao;
import models.Player;

import java.util.List;
import java.util.Optional;

public class ManualAddPlayerExample {

    public static void main(String[] args) {

        HibernatePlayerDao hibernatePlayerDao = new HibernatePlayerDao();

        Player player = new Player();
        player.setName("Vasya");

        hibernatePlayerDao.save(player);

        List<Player> playerFromDb = hibernatePlayerDao.findAll();
        //Optional playerFromDb = hibernatePlayerDao.findById(player.getId());

        System.out.println(playerFromDb);

    }

}
