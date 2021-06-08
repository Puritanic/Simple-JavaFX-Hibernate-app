package zadatak4.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import zadatak4.entities.Fakultet;
import zadatak4.entities.Student;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    public static void createSessionFactory() {
        try {
            Configuration config = new Configuration().configure("hibernate.cfg.xml");
            config.addAnnotatedClass(Student.class);
            config.addAnnotatedClass(Fakultet.class);
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
