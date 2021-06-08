package zadatak4.Utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zadatak4.app.Toast;

public class ViewUtils {
    private static final String bgColor = "-fx-background-color: #f0f0f0";
    public static final String fontStyle = "-fx-font-weight:normal; -fx-color: #f0f0f0; -fx-font-size:11; -fx-font-family: Verdana;";

    public static <T> BorderPane buildView(TableView<T> table, Button[] buttons, String[] captions, EventHandler<ActionEvent> BtnHandler, String[] labels, TextField[] textFields) {
        BorderPane border = new BorderPane();
        border.setTop(createButtonBox(buttons, captions, BtnHandler));
        border.setCenter(createForm(labels, textFields));
        border.setBottom(table);
        border.setStyle(bgColor);
        border.setPadding(new Insets(10, 10, 10, 10));
        return border;
    }

    public static Pane createButtonBox(Button[] buttons, String[] captions, EventHandler<ActionEvent> BtnHandler) {
        int width = 100;
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);

        for (int i = 0; i < captions.length; i++) {
            buttons[i] = new Button(captions[i]);
            buttons[i].setStyle(fontStyle);
            buttons[i].setMinWidth(width);
            buttons[i].setOnAction(BtnHandler);
            box.getChildren().add(buttons[i]);
        }
        return box;
    }

    public static Node createForm(String[] labels, TextField[] textFields) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(20);
        grid.setStyle(fontStyle);
        grid.setVgap(2);

        for (int i = 0; i < labels.length; i++) {
            grid.add(new Label(labels[i] + " :"), 1, i);
            textFields[i] = new TextField();
            grid.add(textFields[i], 2, i);
        }

        textFields[0].setEditable(false);
        textFields[0].setTooltip(new Tooltip("Ovo polje je automatski generisano."));
        return grid;
    }

    public static boolean validateInputs(TextField[] inputs, Stage window) {
        for (TextField tf : inputs) {
            if (tf.getText().isEmpty()) {
                Toast.show("Molimo popunite sva polja.", window);
                System.out.println("[WARN] Molimo popunite sva polja.");
                return false;
            }
        }

        return true;
    }
}
