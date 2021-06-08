package zadatak4.app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Toast {
    private static final int TOAST_TIMEOUT = 2000;
    private static final String style = "-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-font-size: 16;";

    private static Popup createPopup(final String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        Label label = new Label(message);
        label.setStyle(style);
        popup.getContent().add(label);
        return popup;
    }

    public static void show(final String message, Stage stage) {
        final Popup popup = createPopup(message);
        popup.setOnShown(e -> {
            popup.setX(stage.getX() + stage.getWidth() - popup.getWidth() - 20);
            popup.setY(stage.getY() + popup.getHeight() / 2 + 20);
        });
        popup.show(stage);

        new Timeline(new KeyFrame(Duration.millis(TOAST_TIMEOUT),
                ae -> popup.hide())).play();
    }
}