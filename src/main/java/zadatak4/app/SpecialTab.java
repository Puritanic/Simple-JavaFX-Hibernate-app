package zadatak4.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import zadatak4.Utils.HibernateUtil;
import zadatak4.Utils.ViewUtils;
import zadatak4.entities.Student;
import zadatak4.interfaces.TabView;

import java.util.List;

public class SpecialTab extends Tab implements TabView {
    private final TableView<Student> table = new TableView<>();
    private final TextField[] textFields = new TextField[2];
    private final BorderPane view;

    SpecialTab() {
        super();
        super.setText("Special");

        String[] btnCaption = {"Submit Query"};
        String[] labels = {"Grad", "Godina Upisa"};
        Button[] button = new Button[1];

        view = ViewUtils.buildView(table, button, btnCaption, new ButtonHandler(), labels, textFields);

        textFields[0].setEditable(true);
        textFields[0].setTooltip(new Tooltip(""));

        populateTable();

        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!table.getSelectionModel().isEmpty()) {
                    int selectedIdx = table.getSelectionModel().getSelectedIndex();
                    populateForm(selectedIdx);
                }
            }
        });

        super.setContent(view);
    }

    @Override
    public void populateForm(int index) {
    }

    @Override
    public void populateTable() {
    }

    public void populateTable(ObservableList<Student> studentData) {
        table.getItems().clear();
        table.setStyle(ViewUtils.fontStyle);
        table.setItems(studentData);
        TableColumn<Student, Integer> brojIndeksaCol = new TableColumn<>("Broj Indeksa");
        brojIndeksaCol.setCellValueFactory(new PropertyValueFactory<>("brojIndeksa"));
        TableColumn<Student, String> imeCol = new TableColumn<>("Ime");
        imeCol.setCellValueFactory(new PropertyValueFactory<>("ime"));
        TableColumn<Student, String> prezimeCol = new TableColumn<>("Prezime");
        prezimeCol.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        TableColumn<Student, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Student, Integer> IDFakultetaCol = new TableColumn<>("ID Fakulteta");
        IDFakultetaCol.setCellValueFactory(new PropertyValueFactory<>("IDFakulteta"));
        TableColumn<Student, Integer> godinaUpisaCol = new TableColumn<>("Godina upisa");
        godinaUpisaCol.setCellValueFactory(new PropertyValueFactory<>("godinaUpisa"));
        table.getColumns().setAll(brojIndeksaCol, imeCol, prezimeCol, statusCol, IDFakultetaCol, godinaUpisaCol);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Stage window = (Stage) view.getScene().getWindow();

            if (ViewUtils.validateInputs(textFields, window)) {
                String grad = textFields[0].getText();
                int godinaUpisa = Integer.parseInt(textFields[1].getText());

                Session session = HibernateUtil.getCurrentSession();
                session.beginTransaction();

                String hql = "select s from Student s inner join Fakultet f on s.IDFakulteta = f.id where s.godinaUpisa=:godina and f.grad=:grad";
                Query query = session.createQuery(hql);
                query.setParameter("godina", godinaUpisa);
                query.setParameter("grad", grad);

                List<Student> resultList = query.getResultList();
                session.getTransaction().commit();

                populateTable(FXCollections.observableArrayList(resultList));
            }
        }
    }
}
