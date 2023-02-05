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
        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufu-xJiSOxynzT2dbbwlAGaP5Gm-TnGM2IA&usqp=CAU");
        ImageView imageView = new ImageView(image);
        borderPane.setLeft(imageView);

        BorderPane.setAlignment(imageView, Pos.CENTER);
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
        VBox contentText = new VBox(10);
        contentText.setPadding(new Insets(20, 20, 20, 20));
        contentText.setAlignment(Pos.CENTER);
        contentText.getChildren().add(name);
        contentText.getChildren().add(startYear);
        contentText.getChildren().add(endYear);
        contentText.getChildren().add(capital);
        contentText.getChildren().add(founder);
        contentText.getChildren().add(kings);

        borderPane.setCenter(contentText);
        HBox moreInforContainer = new HBox();

        for (int i = 0; i < curSelect.getKings().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("More Info " + curSelect.getKings().get(index).getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new KingDetails(curSelect.getKings().get(index));
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }

        borderPane.setBottom(moreInforContainer);
        BorderPane.setAlignment(moreInforContainer, Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
