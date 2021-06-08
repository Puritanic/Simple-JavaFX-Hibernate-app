package zadatak4.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import zadatak4.Utils.ViewUtils;
import zadatak4.entities.Fakultet;
import zadatak4.interfaces.TabView;

public final class FakultetTab extends Tab implements TabView {
    private final TableView<Fakultet> table = new TableView<>();
    private final TextField[] textFields = new TextField[5];
    private final FakultetController controller = new FakultetController();
    private final BorderPane view;

    FakultetTab() {
        super();
        super.setText("Fakulteti");

        String[] btnCaptions = {"Add New", "Update", "Delete", "Clear Form"};
        String[] labels = {"ID", "Naziv", "Grad", "Adresa", "Telefon"};
        Button[] buttons = new Button[4];

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

    public void populateForm(int index) {
        if (controller.getFakultetData().isEmpty()) return;

        Fakultet c = controller.getFakultetData().get(index);
        textFields[0].setText(String.valueOf(c.getId()));
        textFields[1].setText(c.getNaziv());
        textFields[2].setText(c.getGrad());
        textFields[3].setText(c.getAdresa());
        textFields[4].setText(c.getTelefon());
    }

    private Fakultet createFakultet(boolean withId) {
        if (withId) {
            return new Fakultet(
                    Integer.parseInt(textFields[0].getText()),
                    textFields[1].getText(), textFields[2].getText(),
                    textFields[3].getText(), textFields[4].getText());
        }
        return new Fakultet(
                textFields[1].getText(), textFields[2].getText(),
                textFields[3].getText(), textFields[4].getText());
    }

    public void populateTable() {
        table.getItems().clear();
        table.setStyle(ViewUtils.fontStyle);
        table.setItems(controller.getFakultetData());
        TableColumn<Fakultet, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Fakultet, String> nazivCol = new TableColumn<>("Naziv");
        nazivCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        TableColumn<Fakultet, String> gradCol = new TableColumn<>("Grad");
        gradCol.setCellValueFactory(new PropertyValueFactory<>("grad"));
        TableColumn<Fakultet, String> adresaCol = new TableColumn<>("Adresa");
        adresaCol.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        TableColumn<Fakultet, String> telefonCol = new TableColumn<>("Telefon");
        telefonCol.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        table.getColumns().setAll(idCol, nazivCol, gradCol, adresaCol, telefonCol);
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
                        controller.addFakultet(createFakultet(false));
                    } else {
                        shouldUpdate = false;
                    }
                    break;
                case "Update":
                    if (ViewUtils.validateInputs(textFields, window)) {
                        controller.updateFakultet(createFakultet(true));
                    } else {
                        shouldUpdate = false;
                    }
                    break;
                case "Delete":
                    if (ViewUtils.validateInputs(textFields, window)) {
                        controller.removeFakultet(Integer.parseInt(textFields[0].getText()));
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
