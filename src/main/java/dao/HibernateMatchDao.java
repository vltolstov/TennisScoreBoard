package dao;

import exceptions.DatabaseOperationException;
import hibernate.HibernateUtils;
import jakarta.persistence.PersistenceException;
import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateMatchDao implements MatchDao {

    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            matches = session.createQuery("from Match").list();
        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find all matches");
        }

        return matches;
    }

    @Override
    public Optional<Match> findById(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.find(Match.class, id));
        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find match with id " + id);
        }
    }

    @Override
    public Match save(Match match) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
            return match;
        } catch (IllegalStateException e) {
            transactionSafeRollBack(transaction);
            throw new DatabaseOperationException("Unexpected error while saving match with id " + match.getId() + " to database");
        }
    }

    @Override
    public List<Match> findByPlayer(Player player) {
        List<Match> matches = new ArrayList<>();

        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            matches = session
                    .createQuery("from Match match where match.firstPlayer = :player or match.secondPlayer = :player", Match.class)
                    .setParameter("player", player)
                    .list();
        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find matches for player " + player.getName());
        }

        return matches;
    }

    private void transactionSafeRollBack(Transaction transaction){
        try {
            if (transaction != null) transaction.rollback();
        } catch (Exception e) {
            System.out.println("Rollback failed" + e.getMessage());
        }
    }
}
