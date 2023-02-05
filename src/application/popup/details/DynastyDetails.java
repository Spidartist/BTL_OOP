package application.popup.details;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.dynasty.Dynasty;
import objects.figure.King;

public class DynastyDetails {
    public DynastyDetails(Dynasty curSelect, ObservableList<King> listKing) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Figure Detail");
        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));

        Image image = new Image(
                "https://vozer.vn/storage/images/tom-tat-lich-su-viet-nam-bang-mot-bai-tho-05494151.jpg");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(233);
        imageView.setFitHeight(145);
        Label name = new Label("Tên Triều đại: " + curSelect.getName());
        Label startYear = new Label("Năm bắt đầu: " + curSelect.getStartYear());
        Label endYear = new Label("Năm kết thúc: " + curSelect.getEndYear());
        Label capital = new Label("Thủ dô: " + curSelect.getCapital());
        Label founder = new Label("Người sáng lập: " + curSelect.getFounder().getTen());
        String strKing = "";
        LinkedList<King> newKing = new LinkedList<King>();
        for (int i = 0; i < curSelect.getKings().size(); i++) {

            strKing += curSelect.getKings().get(i).getTen() + ",";
            for (int j = 0; j < listKing.size(); j++) {
                if (curSelect.getKings().get(i).getTen().toLowerCase()
                        .indexOf(listKing.get(j).getTen().toLowerCase()) != -1) {
                    newKing.add(listKing.get(j));
                }
            }
        }
        Label kings = new Label(strKing);
        curSelect.setKings(newKing);
        kings.setWrapText(true);

        name.getStyleClass().add("text-color");
        startYear.getStyleClass().add("text-color");
        endYear.getStyleClass().add("text-color");
        capital.getStyleClass().add("text-color");
        founder.getStyleClass().add("text-color");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(25);
        HBox picturebox = new HBox();

        // picturebox.setSpacing(20);
        picturebox.getChildren().add(imageView);
        // picturebox.setStyle("-fx-border-color: white; -fx-border-width: 3px;
        // -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

        VBox labelbox = new VBox();
        // labelbox.setSpacing(20);
        labelbox.getChildren().addAll(name, startYear, endYear, capital, founder);

        hbox.getChildren().addAll(picturebox, labelbox);

        borderPane.setCenter(hbox);
        picturebox.setAlignment(Pos.CENTER_LEFT);
        labelbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setAlignment(Pos.CENTER);

        HBox moreInforContainer = new HBox();
        for (int i = 0; i < curSelect.getKings().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("" + curSelect.getKings().get(index).getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new KingDetails(curSelect.getKings().get(index));
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }

        borderPane.setBottom(moreInforContainer);
        BorderPane.setAlignment(moreInforContainer, Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
