package application.popup.details;

import objects.event.SuKien;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuKienDetails {
    public SuKienDetails(SuKien curSelect) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Figure Detail");
        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));

        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrDuM8-H23BUUNCV6C90QbUqbWV2iyQ7b_fQ&usqp=CAU");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(233);
        imageView.setFitHeight(145);
        // imageView.setStyle("-fx-border-color: red; -fx-border-width:
        // medium;-fx-border-style: solid;");
        imageView.getStyleClass().add("border-style");

        Label ten = new Label("Tên Sự Kiện: " + curSelect.getTen());
        Label thoi_gian = new Label("Thời gian diễn ra: " + curSelect.getThoi_gian());
        Label dia_diem = new Label("Địa điểm : " + curSelect.getDia_diem());

        ten.getStyleClass().add("text-color");
        thoi_gian.getStyleClass().add("text-color");
        dia_diem.getStyleClass().add("text-color");

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(25);
        HBox picturebox = new HBox();
        // picturebox.setSpacing(20);
        picturebox.getChildren().add(imageView);
        picturebox.setStyle(
                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

        VBox labelbox = new VBox();
        // labelbox.setSpacing(20);
        labelbox.getChildren().addAll(ten, thoi_gian, dia_diem);

        hbox.getChildren().addAll(picturebox, labelbox);

        VBox contentText = new VBox();

        vbox.getChildren().addAll(hbox, contentText);
        borderPane.setCenter(vbox);
        picturebox.setAlignment(Pos.CENTER_LEFT);
        labelbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.setAlignment(Pos.CENTER);
        contentText.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
