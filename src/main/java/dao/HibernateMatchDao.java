package dao;

import exceptions.DatabaseOperationException;
import hibernate.HibernateUtils;
import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class HibernateMatchDao implements MatchDao {

    @Override
    public List<Match> findAll(int page, int pageSize) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            String hql = "FROM Match match";
            Query<Match> query = session.createQuery(hql, Match.class);

            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.list();

        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find all matches");
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
    public List<Match> findByPlayer(Player player, int page, int pageSize) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {

            String hql = "from Match match where match.firstPlayer = :player or match.secondPlayer = :player";
            Query<Match> query = session.createQuery(hql, Match.class);

            query.setParameter("player", player);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.list();

        } catch (Exception e) {
            throw new DatabaseOperationException("Could not find matches for player " + player.getName());
        }
    }

    private void transactionSafeRollBack(Transaction transaction){
        try {
            if (transaction != null) transaction.rollback();
        } catch (Exception e) {
            System.out.println("Rollback failed" + e.getMessage());
        }
    }

    @Override
    public Long getTotalMatchesCount() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(match) FROM Match match";
            Query<Long> query = session.createQuery(hql, Long.class);

            return query.uniqueResult();
        }
    }

    @Override
    public java.lang.Long getTotalMatchesCountByPlayer(Player player) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(match) FROM Match match WHERE match.firstPlayer = :player OR match.secondPlayer = :player";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("player", player);

            return query.uniqueResult();
        }
    }
}
