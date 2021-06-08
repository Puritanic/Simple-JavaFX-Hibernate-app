package zadatak4.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import zadatak4.Utils.HibernateUtil;

/**
 * Kreirati tabele fakultet (id, naziv, grad, adresa, kontakt telefon) i student (broj indeksa, id fakulteta, ime, prezime, godina upisa, status).
 * Napraviti JavaFX aplikaciju koja omogućava unos svih podataka u MySQL bazu, brisanje, izmenu i čitanje svih podataka iz baze.
 * Pored toga, omogućiti da korisnik unese grad u kome se nalazi fakultet i godinu upisa studenta na šta aplikacija kao odgovor
 * prikazuje sve studente koji studiraju na fakultetu koji se nalazi u unetom gradu, a upisani su unete godine.
 */
public class Main extends Application {
    private final TabPane tabPane = new TabPane();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Domaći zadatak CS102-DZ11");
        HibernateUtil.createSessionFactory();
//        Utils.seedFakultetData();
//        Utils.seedStudentData();

        tabPane.setTabMinWidth(200);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab fakultetTab = new FakultetTab();
        Tab studentTab = new StudentTab();
        Tab specialTab = new SpecialTab();

        tabPane.getTabs().addAll(fakultetTab, studentTab, specialTab);

        primaryStage.setScene(new Scene(tabPane, 800, 650));
        primaryStage.show();
    }
}
