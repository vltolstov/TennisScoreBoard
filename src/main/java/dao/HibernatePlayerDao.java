package dao;

import exceptions.DatabaseOperationException;
import hibernate.HibernateUtils;
import jakarta.persistence.PersistenceException;
import models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernatePlayerDao implements PlayerDao {

    @Override
    public List<Player> findAll() {

        List<Player> players = new ArrayList<>();

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            players = session.createQuery("from Player").list();
        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find all players");
        }

        return players;
    }

//    @Override
//    public Optional<Player> findById(Integer id) {
//        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//            return Optional.ofNullable(session.find(Player.class, id));
//        } catch (Exception e) {
//            throw new DatabaseOperationException("Could not find player with id " + id);
//        }
//    }

    @Override
    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Player player = session
                    .createQuery("FROM Player WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
            return Optional.ofNullable(player);
        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find player with name " + name);
        }
    }

    @Override
    public Player save(Player player) {

        Transaction transaction = null;

        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            return player;
        } catch (ConstraintViolationException e) {
            transactionSafeRollBack(transaction);
            throw new DatabaseOperationException("Player name " + player.getName() + " not unique");
        } catch (PersistenceException e) {
            transactionSafeRollBack(transaction);
            throw new DatabaseOperationException("Could not save player " + player.getName() + " to database");
        } catch (IllegalStateException e) {
            transactionSafeRollBack(transaction);
            throw new DatabaseOperationException("Unexpected error while saving player " + player.getName() + " to database");
        }
    }

    private void transactionSafeRollBack(Transaction transaction){
        try {
            if (transaction != null) transaction.rollback();
        } catch (Exception e) {
            System.out.println("Rollback failed" + e.getMessage());
        }
    }
}