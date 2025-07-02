package hibernate;

import lombok.Getter;
import models.Match;
import models.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Player.class)
                    .addAnnotatedClass(Match.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

}
