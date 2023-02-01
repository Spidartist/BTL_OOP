package application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import objects.figure.Figure;
import objects.figure.King;

public class PopUpWinDow {
    public Popup getPopUpWindow(Object obj) {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        if (obj.getClass() == King.class) {
            King curSelect = (King) obj;
            Label secondLabel = new Label(curSelect.getTen());
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            Scene secondScene = new Scene(secondaryLayout, 230, 100);
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            // newWindow.setWidth(200);
            // newWindow.setHeight(200);
            newWindow.show();
        }
        else if (obj.getClass() == Figure.class) {
            King curSelect = (King) obj;
            Label secondLabel = new Label(curSelect.getTen());
            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(secondLabel);
            Scene secondScene = new Scene(secondaryLayout, 230, 100);
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);
            // newWindow.setWidth(200);
            // newWindow.setHeight(200);
            newWindow.show();
        } else {
            
        }
        return popup;
    }
}
