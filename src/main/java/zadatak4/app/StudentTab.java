package zadatak4.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import zadatak4.Utils.ViewUtils;
import zadatak4.entities.Student;
import zadatak4.interfaces.TabView;

public class StudentTab extends Tab implements TabView {
    private final TableView<Student> table = new TableView<>();
    private final TextField[] textFields = new TextField[6];
    private final StudentController controller = new StudentController();
    private final BorderPane view;

    StudentTab() {
        super();
        super.setText("Studenti");

        String[] btnCaptions = {"Add New", "Update", "Delete", "Clear Form"};
        Button[] buttons = new Button[4];
        String[] labels = {"Broj Indeksa", "Ime", "Prezime", "Status", "Godina Upisa", "ID Fakulteta"};

        view = ViewUtils.buildView(table, buttons, btnCaptions, new ButtonHandler(), labels, textFields);
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
        if (controller.getStudentData().isEmpty()) return;

        Student c = controller.getStudentData().get(index);
        textFields[0].setText(String.valueOf(c.getBrojIndeksa()));
        textFields[1].setText(c.getIme());
        textFields[2].setText(c.getPrezime());
        textFields[3].setText(c.getStatus());
        textFields[4].setText(String.valueOf(c.getGodinaUpisa()));
        textFields[5].setText(String.valueOf(c.getIDFakulteta()));
    }

    @Override
    public void populateTable() {
        table.getItems().clear();
        table.setStyle(ViewUtils.fontStyle);
        table.setItems(controller.getStudentData());
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

    private Student createStudent(boolean withId) {
        if (withId) {
            return new Student(
                    Integer.parseInt(textFields[0].getText()),
                    textFields[1].getText(), textFields[2].getText(),
                    textFields[3].getText(), Integer.parseInt(textFields[4].getText()),
                    Integer.parseInt(textFields[4].getText()));
        }
        return new Student(
                textFields[1].getText(), textFields[2].getText(),
                textFields[3].getText(), Integer.parseInt(textFields[4].getText()),
                Integer.parseInt(textFields[4].getText()));
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String selectedAction = ((Button) event.getSource()).getText();
            boolean shouldUpdate = true;

            Stage window = (Stage) view.getScene().getWindow();

            switch (selectedAction) {
                case "Add New":
                    if (ViewUtils.validateInputs(textFields, window)) {
                        controller.addStudent(createStudent(false));
                    } else {
                        shouldUpdate = false;
                    }
                    break;
                case "Update":
                    if (ViewUtils.validateInputs(textFields, window)) {
                        controller.updateStudent(createStudent(true));
                    } else {
                        shouldUpdate = false;
                    }
                    break;
                case "Delete":
                    if (ViewUtils.validateInputs(textFields, window)) {
                        controller.removeStudent(Integer.parseInt(textFields[0].getText()));
                    } else {
                        shouldUpdate = false;
                    }
                    break;
                case "Clear Form":
                    shouldUpdate = false;
                    for (TextField tf : textFields) {
                        if (tf != null) tf.clear();
                    }
                    break;
            }
            if (shouldUpdate) {
                populateTable();
                populateForm(0);
            }
        }
    }
}
