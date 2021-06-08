package zadatak4.Utils;

import com.github.javafaker.Faker;
import org.hibernate.Session;
import zadatak4.entities.Fakultet;
import zadatak4.entities.Student;

import java.util.Random;

public final class DBUtils {
    private DBUtils() {}

    public static void seedStudentData() {
        Random rand = new Random();
        Faker faker = new Faker();
        // create session
        Session session = HibernateUtil.getCurrentSession();

        try {
            System.out.println("Creating student seed data...");
            // start a transaction
            session.beginTransaction();

            // seed student data
            for (int i = 0; i < 150; i++) {
                String status = "aktivan";
                if (i % 3 == 0) {
                    status = "neaktivan";
                }
                // create a student object
                Student student = new Student(
                        faker.name().firstName(), faker.name().lastName(),
                        status, rand.nextInt(10) + 2010,
                        rand.nextInt(50) + 1);
                // save the student object
                session.save(student);
            }
            // commit transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void seedFakultetData() {
        Faker faker = new Faker();

        Session session = HibernateUtil.getCurrentSession();

        try {
            System.out.println("Creating fakultet seed data...");
            // start a transaction
            session.beginTransaction();

            // seed student data
            for (int i = 0; i < 60; i++) {
                // create a student object
                Fakultet fakultet = new Fakultet(
                        faker.university().name(), faker.gameOfThrones().city(),
                        faker.address().streetAddress(), faker.phoneNumber().phoneNumber()
                );
                // save the student object
                session.save(fakultet);
            }
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Seeding done!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
