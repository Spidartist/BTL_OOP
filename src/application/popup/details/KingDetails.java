package application.popup.details;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import objects.figure.King;

public class KingDetails {
    public KingDetails(King curSelect) {
        Label secondLabel = new Label(curSelect.getTen());
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 230, 100);
        Stage newWindow = new Stage();
        // newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setTitle("King details");
        newWindow.setScene(secondScene);
        // newWindow.setWidth(200);
        // newWindow.setHeight(200);
        newWindow.show();
    }
}
