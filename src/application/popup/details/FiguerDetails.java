package application.popup.details;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import objects.figure.Figure;

public class FiguerDetails {

    public FiguerDetails(Figure curSelect) {
        BorderPane popupBorderPane = new BorderPane();
        Scene secondScene = new Scene(popupBorderPane, 500, 500);
        ImageView img = new ImageView(
                "https://images.squarespace-cdn.com/content/v1/54b7b93ce4b0a3e130d5d232/1519987020970-8IQ7F6Z61LLBCX85A65S/icon.png?format=1000w");
        double imgWidth = secondScene.getWidth() / 2;
        double imgHeight = secondScene.getHeight() / 2;
        img.setFitWidth(imgWidth);
        img.setFitHeight(imgHeight);
        popupBorderPane.setLeft(img);
        Label secondLabel = new Label(curSelect.getTen());
        popupBorderPane.setCenter(secondLabel);
        // StackPane secondaryLayout = new StackPane();
        // secondaryLayout.getChildren().add(secondLabel);
        Stage newWindow = new Stage();
        // // newWindow.initModality(Modality.WINDOW_MODAL);
        // newWindow.setTitle("Figure");
        newWindow.setScene(secondScene);
        // newWindow.setWidth(200);
        // newWindow.setHeight(200);
        newWindow.show();
    }

}
